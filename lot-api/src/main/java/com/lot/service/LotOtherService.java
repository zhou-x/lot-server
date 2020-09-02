package com.lot.service;

public interface LotOtherService {
    String getAboutUs();
    void updateAboutUs(String string);

    String getContactUs();
    void updateContactUs(String string);

    String getUserProtocol();
    void updateUserProtocol(String string);

    String getPrivacyPolicy();
    void updatePrivacyPolicy(String string);

    String getDemandExample();
    void updateDemandExample(String string);

    String getCommonProblem();
    void updateCommonProblem(String string);

    String getStartImgUrl();
    void updateStartImgUrl(String string);
}
