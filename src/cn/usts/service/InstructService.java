package cn.usts.service;

import cn.usts.pojo.Instructs;

import java.util.List;
import java.util.Optional;

public interface InstructService {

    void save(Instructs instructs);

    List<Instructs> queryAboutMe(Instructs instructs);

    int getAboutMeSize(Instructs instructs);

    Optional<Instructs> findById(Integer id);

    void update(Instructs instructs);

}
