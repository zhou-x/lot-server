package com.lot.serviceImpl;


import com.lot.entity.LotOtherEntity;
import com.lot.mapper.LotOtherMapper;
import com.lot.service.LotOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LotOtherServiceImpl implements LotOtherService {

    @Autowired
    private LotOtherMapper lotOtherMapper;

    @Override
    public String getAboutUs() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getAboutUs();
    }

    @Override
    public void updateAboutUs(String string) {
        lotOtherMapper.updateABOUT_US(string);
    }

    @Override
    public String getContactUs() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getContactUs();
    }

    @Override
    public void updateContactUs(String string) {
        lotOtherMapper.updateCONTACT_US(string);
    }

    @Override
    public String getUserProtocol() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        System.out.println(lotOtherEntity.getUserProtocol());
        return lotOtherEntity.getUserProtocol();
    }

    @Override
    public void updateUserProtocol(String string) {
        lotOtherMapper.updateUSER_PROTOCOL(string);
    }

    @Override
    public String getPrivacyPolicy() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getPrivacyPolicy();
    }

    @Override
    public void updatePrivacyPolicy(String string) {
        lotOtherMapper.updatePRIVACY_POLICY(string);
    }

    @Override
    public String getDemandExample() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getDemandExample();
    }

    @Override
    public void updateDemandExample(String string) {
        lotOtherMapper.updateDEMAND_EXAMPLE(string);
    }

    @Override
    public String getCommonProblem() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getCommonProblem();
    }

    @Override
    public void updateCommonProblem(String string) {
        lotOtherMapper.updateCOMMON_PROBLEM(string);
    }

    @Override
    public String getStartImgUrl() {
        LotOtherEntity lotOtherEntity = lotOtherMapper.get();
        return lotOtherEntity.getStartImg();
    }

    @Override
    public void updateStartImgUrl(String string) {
        lotOtherMapper.updateStartImgUrl(string);
    }
}
