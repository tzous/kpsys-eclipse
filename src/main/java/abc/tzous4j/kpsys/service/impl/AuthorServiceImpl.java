package abc.tzous4j.kpsys.service.impl;

import abc.tzous4j.kpsys.dao.TbuserMapper;
import abc.tzous4j.kpsys.model.Tbuser;
import abc.tzous4j.kpsys.service.IAuthorService;
import abc.tzous4j.utils.MD5Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZQ on 2016-05-06.
 */
@Service
public class AuthorServiceImpl implements
        IAuthorService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Tbuser CheckAuthor(String loginid, String passwd, int ip) {
        if(null == loginid || loginid.isEmpty() || null == passwd || passwd.isEmpty()) {
            return null;
        }
        Tbuser tbuser = new Tbuser();
        boolean bret = false;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TbuserMapper tbuserDao = session.getMapper(TbuserMapper.class);
            String md5passwd = MD5Util.MD5(passwd);
            tbuser = tbuserDao.selectByPrimaryKey(loginid);
            if(tbuser != null) {
                if(tbuser.getUpasswd().compareToIgnoreCase(md5passwd) == 0) {
                    bret = true;
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }
        if(bret)
            return tbuser;
        else
            return null;
    }
}
