package abc.tzous4j.kpsys.dao;

import abc.tzous4j.kpsys.model.Tbproj;

public interface TbprojMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    int deleteByPrimaryKey(Integer pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    int insert(Tbproj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    int insertSelective(Tbproj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    Tbproj selectByPrimaryKey(Integer pid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    int updateByPrimaryKeySelective(Tbproj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbproj
     *
     * @mbg.generated Sat Jan 05 20:22:38 CST 2019
     */
    int updateByPrimaryKey(Tbproj record);
}