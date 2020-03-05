package cn.usts.service.impl;

import cn.usts.dao.InstructionsDao;
import cn.usts.pojo.Instructs;
import cn.usts.service.InstructService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InstructImpl implements InstructService {

    @Resource
    private InstructionsDao instructionsDao;

    @Override
    public void save(Instructs instructs) {
        instructionsDao.save(instructs);
        log.info("保存指令信息数据：{}", instructs);
    }

    @Override
    public List<Instructs> queryAboutMe(Instructs instructs) {
        return instructionsDao.queryByBean(instructs);
    }

    @Override
    public int getAboutMeSize(Instructs instructs) {
        return instructionsDao.getCountSize(instructs);
    }

    @Override
    public Optional<Instructs> findById(Integer id) {
        return Optional.ofNullable(instructionsDao.queryById(id.intValue()).get(0));
    }

    @Override
    public void update(Instructs instructs) {
        instructionsDao.update(instructs);
    }
}
