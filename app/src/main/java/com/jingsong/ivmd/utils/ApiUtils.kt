package com.jingsong.patient.utils

import com.jingsong.patient.db.PatientTable
import com.jingsong.patient.model.CharacteristicModel
import com.jingsong.patient.model.CharsModel
import com.jingsong.patient.model.DeviceItemModel
import com.jingsong.patient.model.PersonProfileModel


/**
 * @author hope.chen
 *         QQ:77132995 email:kazeik@163.com
 *         2018 11 11 17:38
 * 类说明:
 */
object ApiUtils {
    val CHAT_MSG = 0
    val CHAT_IMG = 1
    val LEFT_MSG: Int = 0
    val RIGHT_MSG: Int = 1
    val LEFT_IMG: Int = 2
    val RIGHT_IMG: Int = 3

    val characterisMap: HashMap<String, CharsModel> by lazy { HashMap<String, CharsModel>() }
    val allDeviceData: ArrayList<DeviceItemModel> by lazy { ArrayList<DeviceItemModel>() }


    val AppId: String = "2882303761518074072"
    val appKey: String = "5961807477072"

    var token: String? = ""
    val allModel by lazy { ArrayList<PatientTable>() }
    var userInfo: PersonProfileModel? = null
    var chatsModel: List<CharacteristicModel>? = null
    const val getToken = "/api/person/oauth/token"
    const val postSms = "/api/sms"
    const val getversion = "/open/android/upgrade"
    const val imgpath = "/open/static/chat/"
    /**
     * @DELETE 批量删除患者
     */
    const val record = "/api/v1/patient"
    const val updaterecord = "/api/v1/patient/:patientId"
    const val putDefault = "/api/v1/patient/:patientId/default"

    const val putDefaultGrardian = "/api/v1/patient/:patientId/guardian"

    const val deleteguard = "/api/v1/person/guardian"
    /**
     * @GET 获取用户信息
     * @PUT 修改用户信息
     */
    const val getprofile = "/api/v1/person/profile"
//    const val guardian = "/api/v1/person/guardian"
    const val usericon = "/open/static/patient/:personId/avatar/:avatarHash"
    const val usericon1 = "/open/static/person/:personId/avatar/:avatarHash"

    const val devices = "/api/v1/device/model"
    const val deviceinfo = "/api/v1/device/:deviceId"
    const val deviceinfoimg = "/open/static/device/:deviceId/thumb/:pictureHash"
    const val deviceinfopic = "/open/static/device/:deviceId/picture/:pictureHash"

    const val history = "/api/v1/test/history"
    const val deletetest = "/api/v1/test"
    const val testmonth = "/api/v1/test/monthly"
    const val testday = "/api/v1/test/daily"
    const val testinfo = "/api/v1/test/:devMsgId"

    /**
     * 获取所有医护列表
     * 添加医护人员
     * 删除医护人员
     */
    const val emplyees = "/api/v1/chat/employees"
    /**
     * 患者端获取未读信息统计
     */
    const val unread = "/api/v1/chat/message/employee/unread"
    /**
     * 发送信息给医护
     * 获取与医护的问答记录
     */
    const val postmsg = "/api/v1/chat/message/employee"
    /**
     * 获取医护头像
     */
    const val getEmployeeAvatar = "/open/static/employee/:employeeId/avatar/:avatarHash"
    /**
     * 获取医护详细
     */
    const val getEmployeeDetail = "/api/v1/chat/employees/detail"
    /**
     * 获取聊天记录中的图片
     */
    const val getChatImg = "/open/v1/chat/{content}"

    const val deleteChatMsg = "/api/v1/chat/message/employee"

    const val getcharacteristic = "/api/v1/indicator/characteristic"
    /**
     * @POST 接受设备检测数据
     */
    const val postTestInfo = "/api/v1/test/message"

    const val proviness = "/api/v1/area/provinces"
    const val cityAndCounties = "/api/v1/area/citiesAndCounties"

    /**
     * @GET 获取患者健康档案详情
     *  @PUT 更新患者健康档案
     */
    const val patientInfo = "/api/v1/patient/:patientId/profile"

    const val getPatientInfo = "/api/v1/chat/persons/detail"

    /**
     * 获取患者详细资料
     */
    const val getPatientMoreInfo = "/api/v1/patient/:patientId/overview"

    /**
     * 获取档案照片
     */
    const val getRecordPicPath = "/open/static/patient/profile/"
    /**
     * @POST 添加档案照片记录
     * @PUT 修改档案照片记录
     * @GET 获取档案照片记录
     * @DELETE 删除档案照片记录
     */
    const val recordPic = "/api/v1/patient/:patientId/profile/pic"

    const val getArea = "/api/v1/area"

    const val postImageFile = "/api/v1/patient/profile/pic/upload"


    const val home = "/api/v1/home"


    /**
     * 获取所有文章分页
     */
    const val article = "/api/v1/article"
    /**
     * 获取推荐文章分页
     */
    const val recommendarticle = "/api/v1/article/recommend"
    /**
     * 获取文章封面
     */
    const val articleImg = "/open/static/article/:coverUrl"
    /**
     * 获取文章正文
     */
    const val articletext = "/api/v1/article/text"

    /**
     * 获取所有检测项目的提醒时间
     */
    const val clock = "/api/v1/clock"
    /**
     * 获取单个检测项目的提醒时间
     */
    const val sigleclock = "/api/v1/clock/indGroup"
    /**
     * 设置单个检测项目的提醒时间
     */
    const val indGroup = "/api/v1/clock/indGroup"

    const val valid="/api/v1/patient/valid"
}

