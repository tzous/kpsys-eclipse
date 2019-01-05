package abc.tzous4j.kpsys.service;

import abc.tzous4j.kpsys.model.Tbuser;

import java.util.List;

/**
 * Created by ZQ on 2016-05-06.
 */
public interface ITbuserService {
    public boolean AddUser(Tbuser Tbuser);
    public boolean UpdateUser(Tbuser Tbuser);
    public boolean DeleteUser(Tbuser Tbuser);
    public Tbuser FindUserByUname(String uname);
    public List<Tbuser> FindUsers(Integer offset,Integer limit);
    public Integer CountAll();
    public boolean ModiPasswd(String uname,String oldpass,String newpass);
}
