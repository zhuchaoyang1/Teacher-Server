package cn.usts.service.impl;

import cn.usts.dao.MarjorDao;
import cn.usts.pojo.College;
import cn.usts.pojo.Marjor;
import cn.usts.service.CollegeService;
import cn.usts.service.MarjorService;
import cn.usts.util.websocket.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarjorServiceImpl implements MarjorService {

    @Autowired
    private MarjorDao marjorDao;
    @Autowired
    private SpringWebSocketHandler springWebSocketHandler;
    @Autowired
    private CollegeService collegeService;

    @Override
    public int saveMarjor(Marjor marjor) {
        return marjorDao.save(marjor);
    }

    @Override
    public List<Marjor> queryAll() {
        return marjorDao.queryAll();
    }

    @Override
    public void delete(Marjor marjor) {
        marjorDao.delete(marjor);
    }

    @Override
    public String batchImport(Map<String, List<Marjor>> map, String socketId) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            // 校验Key是否存在
            if (!this.validateCurrentSheetName(key)) {
                stringBuilder.append("学院名称" + key + "不存在，请不要在模板中自定义Sheet工作单元");
                springWebSocketHandler.sendMessageToUser(socketId, new TextMessage(
                        "Failed： 学院名称" + key + "不存在，请不要在模板中自定义Sheet工作单元"
                ));
                continue;
            }
            // 构造数据并导入数据库  WebSocket推送
            List<Marjor> currentSheetData = map.get(key);
            currentSheetData.forEach(item -> {
                if (!StringUtils.isEmpty(item.getMarjorName())) {
                    item.setCollegeName(key);
                    String pdCurrentMarjor = this.isRepeatImport(item);
                    if (StringUtils.isEmpty(pdCurrentMarjor)) {
                        saveMarjor(item);
                        springWebSocketHandler.sendMessageToUser(socketId, new TextMessage(
                                "Successed：" + item.getMarjorName() + "专业导入成功"
                        ));
                    } else {
                        // 重复导入
                        stringBuilder.append(pdCurrentMarjor);
                        springWebSocketHandler.sendMessageToUser(socketId, new TextMessage(
                                "Failed：" + item.getMarjorName() + "专业已存在于" +
                                        key +
                                        "学院中，不可重复导入"
                        ));
                    }
                } else {
//                    springWebSocketHandler.sendMessageToUser(socketId, new TextMessage(
//                            "Failed：专业名称不能为空"
//                    ));
                }
            });
        }
        return stringBuilder.toString();
    }

    /**
     * @param marjor
     * @return
     */
    @Override
    public List<Marjor> findAllByCollegeName(Marjor marjor) {
        return marjorDao.findAllByCollegeName(marjor);
    }

    @Override
    public List<String> findAllmarjorNamesByCollege(Marjor marjor) {
        return marjorDao.findAllmarjorNamesByCollege(marjor);
    }

    /**
     * 验证当前Sheet是否合法
     * True: 符合条件 可以导入
     * False:  不符合条件 不可导入
     *
     * @return
     */
    private boolean validateCurrentSheetName(String sheetName) {
        for (College college : collegeService.queryAll()) {
            if (college.getCollegeName().equals(sheetName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证当前专业是否已经在当前学院中
     *
     * @param marjor
     * @return
     */
    private String isRepeatImport(Marjor marjor) {
        List<Marjor> marjors = marjorDao.queryByBean(marjor);
        if (marjors.size() > 0) {
            return "专业：" + marjor.getMarjorName() + "已存在于 " + marjor.getCollegeName() + " 学院中";
        }
        return "";
    }


}
