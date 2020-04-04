package cn.usts.service.impl;

import cn.usts.dao.LeavelDao;
import cn.usts.pojo.Leavel;
import cn.usts.service.LeavelService;
import cn.usts.util.JSONBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeavelServiceImpl implements LeavelService {

    @Autowired
    private LeavelDao leavelDao;

    @Override
    public List<Map<String, Object>> queryAll() {
        // 结果集
        List<Map<String, Object>> result = new ArrayList<>();
        // 保证分组后有序
        Map<String, List<Leavel>> groupList =
                leavelDao.queryAll().stream().collect(Collectors.groupingBy(
                        Leavel::getFaleavel, LinkedHashMap::new, Collectors.toList()
                ));
        for (String key : groupList.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("key", key);
            map.put("value", groupList.get(key));
            result.add(map);
        }
        return result;
    }

    @Override
    public void save(Leavel leavel) {
        leavelDao.save(leavel);
    }

    @Override
    public void delete(Leavel leavel) {
        leavelDao.delete(leavel);
    }

    @Override
    public List<Leavel> queryById(Integer id) {
        return leavelDao.queryById(id);
    }

}
