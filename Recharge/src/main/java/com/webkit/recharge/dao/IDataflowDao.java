package com.webkit.recharge.dao;

import com.webkit.recharge.bean.Dataflow;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
@Repository
public interface IDataflowDao {

    Dataflow selectById(Long id);

    String selectNameById(Long id);

    List<Dataflow> selectPackageName(String packageName);

    List<Dataflow> findAll();

    //三网+全国
    List<Dataflow> findAllDefault();

    void create(Dataflow dataflow);

    List<Dataflow> selectMobile(Integer qCellCoreCode);
    List<Dataflow> selectTelecom(Integer qCellCoreCode);
    List<Dataflow> selectUnicom(Integer qCellCoreCode);
}
