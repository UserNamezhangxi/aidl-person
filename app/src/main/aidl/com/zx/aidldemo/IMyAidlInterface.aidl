// IMyAidlInterface.aidl
package com.zx.aidldemo;

import com.zx.aidldemo.Person;
interface IMyAidlInterface {
    /**
     * 除了基本数据类型，其他类型的参数都需要标明方向类型：in(输入), out(输出), inout(输入输出)
     */
    List<Person> add(in Person person);
}
