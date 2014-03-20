package com.gcj.dzh.dao;

import com.gcj.dzh.bean.IOStatBean;
import com.gcj.dzh.bean.NetstatBean;
import com.gcj.dzh.bean.ProcessInfoBean;
import com.gcj.dzh.bean.SystemResourceBean;

public interface ProcessInfoDao {

    public int insert_process_monitor(ProcessInfoBean processInfoBean);

    public int insert_resource_monitor(SystemResourceBean systemResourceBean);

    public int insert_netstat_monitor(NetstatBean netstatBean);

    public int insert_iostat_monitor(IOStatBean ioStatBean);

}