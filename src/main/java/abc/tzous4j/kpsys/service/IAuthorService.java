package abc.tzous4j.kpsys.service;

import abc.tzous4j.kpsys.model.Tbuser;

/**
 * Created by ZQ on 2016-05-06.
 */
public interface IAuthorService {
    public Tbuser CheckAuthor(String loginid, String passwd, int ip);
}
