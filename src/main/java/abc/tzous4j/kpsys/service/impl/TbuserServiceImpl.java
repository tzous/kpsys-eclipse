package abc.tzous4j.kpsys.service.impl;

import abc.tzous4j.kpsys.dao.TbuserMapper;
import abc.tzous4j.kpsys.model.Tbuser;
import abc.tzous4j.kpsys.service.ITbuserService;
import abc.tzous4j.utils.MD5Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZQ on 2016-05-20.
 */
@Service
public class TbuserServiceImpl implements ITbuserService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean AddUser(Tbuser tbuser) {
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            tbuserDao.insert(tbuser);
            bret = true;
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return bret;
    }

    @Override
    public boolean UpdateUser(Tbuser tbuser) {
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            tbuserDao.updateByPrimaryKey(tbuser);
            bret = true;
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return bret;
    }

    @Override
    public boolean DeleteUser(Tbuser tbuser) {
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            tbuserDao.deleteByPrimaryKey(tbuser.getUname());
            bret = true;
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return bret;
    }

    @Override
    public Tbuser FindUserByUname(String uname) {
        Tbuser Tbuser = new Tbuser();
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            Tbuser = tbuserDao.selectByPrimaryKey(uname);
            if(Tbuser != null)
                bret = true;
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        if(bret)
            return Tbuser;
        else
            return null;

    }

    @Override
    public List<Tbuser> FindUsers(Integer offset, Integer limit) {
        List<Tbuser> Tbusers = new ArrayList<Tbuser>();
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            HashMap<String,Integer> offsetlimit = new HashMap<String,Integer>();
            offsetlimit.put("offset",offset);
            offsetlimit.put("limit",limit);
            Tbusers = tbuserDao.findTbuser(offsetlimit);
            bret = true;
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        if(bret)
            return Tbusers;
        else
            return null;
    }

    @Override
    public Integer CountAll() {
        Integer iret = -1;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            iret = tbuserDao.countAll();
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        return iret;
    }

    @Override
    public boolean ModiPasswd(String uname, String oldpass, String newpass) {
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            Tbuser tbuser = tbuserDao.selectByPrimaryKey(uname);
            if(tbuser != null) {
                if(tbuser.getUpasswd().compareToIgnoreCase(MD5Util.MD5(oldpass)) == 0) {
                    tbuser.setUpasswd(MD5Util.MD5(newpass));
                    tbuserDao.updateByPrimaryKey(tbuser);
                    bret = true;
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }

        return bret;
    }
}
