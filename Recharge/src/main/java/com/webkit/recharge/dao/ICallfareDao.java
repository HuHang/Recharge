package com.webkit.recharge.dao;

import com.webkit.recharge.bean.Callfare;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
@Repository
public interface ICallfareDao {
    List<Callfare> selectByName(String packageName);

    Callfare selectById(Long id);

    String selectNameById(Long id);

    List<Callfare> findAll();

    //三网+全国
    List<Callfare> findAllDefault();

    void create(Callfare callfare);

    Integer delete(Long id);

    Integer batchRemove(List<Long> items);

    List<Callfare> selectMobile(Integer qCellCoreCode);
    List<Callfare> selectTelecom(Integer qCellCoreCode);
    List<Callfare> selectUnicom(Integer qCellCoreCode);
}
