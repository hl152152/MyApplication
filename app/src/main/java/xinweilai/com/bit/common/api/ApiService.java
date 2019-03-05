package xinweilai.com.bit.common.api;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by 空 on 2017/6/8 0008.
 */

public interface ApiService {

    //获取验证码图片
    @GET("common/getRandomPicCode.do")
    Observable<ResponseBody> getVcode(@Query("timestamp") long timestamp);

    //登录
    @FormUrlEncoded
    @POST("user/login.do")
    Observable<Data<User>> login(@Field("loginAccount") String loginAccount, @Field("password") String password,
                                 @Field("vcode") String vcode, @Field("device_id") String device_id);

    //获取用户权限模块
    @FormUrlEncoded
    @POST("menu/getUserMenu.do")
    Observable<ArrayList<HomeType>> getUserMenu(@Field("device_id") String device_id, @Field("user_id") int user_id);

    //项目查询
    @FormUrlEncoded
    @POST("projectBase/selectProjects.do")
    Observable<Data<Page<Project>>> selectProjects(@FieldMap HashMap<String, String> hashMap);

    //项目预警信息
    @FormUrlEncoded
    @POST("projectAlarm/queryHistoryInfo.do")
    Observable<Page<projectwaringFragment.ItemObecj>> queryHistoryInfo(@Field("projectId") long project_id);

    //项目基本信息
    @FormUrlEncoded
    @POST("projectBase/queryBaseInfo.do")
    Observable<Data<Project>> queryBaseInfo(@Field("project_id") long project_id);

    // 项目简介  html
    @FormUrlEncoded
    @POST("projectBase/getProjectIntroduction.do")
    Observable<ResponseBody> getProjectIntroduction(@Field("project_id") long project_id);

    // 获取项目年度计划列表
    @FormUrlEncoded
    @POST("projectAnnualPlan/queryProjectAnnualPlanList.do")
    Observable<Page<YearPlan>> queryProjectAnnualPlanList(@Field("project_id") Long project_id, @Field("page") Integer page);

    // 获取项目年度计划详情
    @FormUrlEncoded
    @POST("projectAnnualPlan/queryProjectAnnualPlanInfo.do")
    Observable<Data<YearPlan>> queryProjectAnnualPlanInfo(@Field("project_id") long project_id,
                                                          @Field("plan_year") int plan_year);

    // 获取项目前期信息
    @FormUrlEncoded
    @POST("proAnnualPlanPreStage/queryProjectPreStageList.do")
    Observable<Page<EarlyInfo>> queryProjectPreStageList(@FieldMap HashMap<String, String> hashMap);

    // 获取年度
    @FormUrlEncoded
    @POST("projectAnnualPlan/getList.do")
    Observable<Data<Page<Project>>> projectAnnualPlanList(@Field("page") int page, @Field("uda") boolean uda,
                                                          @Field("audit_status") String audit_status);

    //  获取相关责任单位  这里需要传一个参数 识别成FormBody ，此参数传null
    @FormUrlEncoded
    @POST("org/getAvailableList.do")
    Observable<Data<ArrayList<Branch>>> getAvailableList(@Field("page") Integer page);

    // 获取项目进展汇报列表
    @FormUrlEncoded
    @POST("projectProgress/queryProjectProgressList.do")
    Observable<Page<ProjectTimeline>> queryProjectProgressList(@Field("project_id") Long project_id,
                                                               @Field("page") Integer page);


    // 项目视频监控源
    @FormUrlEncoded
    @POST("projectWebcam/queryWebcamList.do")
    Observable<Page<Video>> queryWebcamList(@Field("project_id") long projectId, @Field("enabled") boolean enabled);

    // 项目视频监控源直播流
    @FormUrlEncoded
    @POST("projectWebcam/getWebcamLiveInfo.do")
    Observable<Data<String>> getWebcamLiveInfo(@Field("project_webcam_id") long project_webcam_id);

    //    // 外部进入 获取项目进展汇报列表
//    @FormUrlEncoded
//    @POST("projectProgress/queryOwnedProgressList.do")
//    Observable<Page<ProjectTimeline>> queryOwnedProgressList(@Field("audit_status") String audit_status,
//                                                             @Field("page") Integer page, @Field("uda") boolean uda);
    // 外部进入 获取项目进展汇报列表
    @FormUrlEncoded
    @POST("projectProgress/queryOwnedProgressList.do")
    Observable<Page<ProjectTimeline>> queryOwnedProgressList(@FieldMap HashMap<String, String> hashMap);


    // 获取项目进展汇报列表
    @FormUrlEncoded
    @POST("projectProgress/queryOwnedProgressList.do")
    Observable<Page<ProjectTimeline>> queryOwnedProgressList(@Field("userFlag") String userFlag, @Field("uda") boolean uda,
                                                             @Field("audit_status") String audit_status, @Field("page") int page);

    // 项目进展汇报详情
    @FormUrlEncoded
    @POST("projectProgress/getProjectProgressDetailInfo.do")
    Observable<Data<ProjectTimeline>> getProjectProgressDetailInfo(@Field("pro_plan_progress_id") long pro_plan_progress_id);

    //  获取项目动态列表  不传id 工作动态  传 id 项目动态
    @FormUrlEncoded
    @POST("projectWorkTrend/queryProjectWorkTrendList.do")
    Observable<Page<ProjectWorkTrend>> queryProjectWorkTrendList(@Field("project_id") Long project_id, @Field("page") int page);

    //  审核通过
    @FormUrlEncoded
    @POST("projectAnnualPlan/accept.do")
    Observable<Data> acceptProjectAnnualPlan(@Field("plan_id") long plan_id, @Field("remark") String remark);

    //  审核拒绝
    @FormUrlEncoded
    @POST("projectAnnualPlan/reject.do")
    Observable<Data> rejectProjectAnnualPlan(@Field("plan_id") long plan_id, @Field("remark") String remark);

    //  审核拒绝
    @FormUrlEncoded
    @POST("projectBase/reject.do")
    Observable<Data> reject(@Field("project_id") long project_id, @Field("remark") String remark);

    //  审核通过
    @FormUrlEncoded
    @POST("projectBase/accept.do")
    Observable<Data> accept(@Field("project_id") long project_id, @Field("remark") String remark);

    //  填写项目进展汇报(月报，旬报等)
    @Multipart
    @POST("projectProgress/updateProgress.do")
    Observable<Data> updateProgress(@PartMap Map<String, RequestBody> bodyMap);

    @POST("media/upload")
    Observable<Data> updateProgress(@Body RequestBody body);

    //  提交结项
    @Multipart
    @POST("projectBase/submitProjectCloseEval.do")
    Observable<Data> submitProjectCloseEval(@PartMap Map<String, RequestBody> bodyMap);

    // 提交前期申请审批
    @Multipart
    @POST("proAnnualPlanPreStage/startStage.do")
    Observable<Data> startStage(@PartMap Map<String, RequestBody> bodyMap);

    @Multipart
    @POST("proAnnualPlanPreStage/completeStage.do")
    Observable<Data> completeStage(@PartMap Map<String, RequestBody> bodyMap);

    //文件上传
    @POST("base/fileUpload.do")
    Observable<ArrayList<Pic>> upPic(@Body RequestBody body);

    //项目结项审核
    @FormUrlEncoded
    @POST("projectProgress/submitProgressApproval.do")
    Observable<Data> submitProgressApproval(@FieldMap Map<String, String> map);

    //项目结项审核
    @FormUrlEncoded
    @POST("todoTask/queryOwnedTaskList.do")
    Observable<Page<KnotComment>> queryOwnedTaskList(@Field("page") Integer page);

    //填写新的项目前期信息
    @FormUrlEncoded
    @POST("proAnnualPlanPreStage/batchAdd.do")
    Observable<Data> batchAdd(@FieldMap Map<String, String> map);

    //获取项目前期定义
    @FormUrlEncoded
    @POST("prepareStage/getList.do")
    Observable<Data> getList(@Field("stage_type") String stage_type);

    //获取项未选择的 前期定义
    @FormUrlEncoded
    @POST("prepareStage/getUnSelected.do")
    Observable<Data<ArrayList<EarlyInfo>>> getUnSelected(@FieldMap Map<String, String> map);

    //查询登录人员权限范围内的项目动态数据
    @FormUrlEncoded
    @POST("projectWorkTrend/queryOwnedProjectWorkTrendList.do")
    Observable<Page<ProjectWorkTrend>> queryOwnedProjectWorkTrendList(@FieldMap Map<String, String> map);

    //获取项目列表
    @FormUrlEncoded
    @POST("projectBase/getSimples.do")
    Observable<Data<ArrayList<Project>>> getSimples(@Field("audit_status") String audit_status,
                                                    @Field("enabled") String enabled, @Field("uda") boolean uda);

    //获取所属类型列表
    @FormUrlEncoded
    @POST("systemCategory/getListByGroup.do")
    Observable<Data<ArrayList<BelongType>>> getListByGroup(@Field("groupCode") String groupCode);

    //项目工作动态审批
    @FormUrlEncoded
    @POST("projectWorkTrend/submitProjectWorkTrendApproval.do")
    Observable<Data> submitProjectWorkTrendApproval(@FieldMap HashMap<String, String> map);

    // 获取项目动态详情
    @FormUrlEncoded
    @POST("projectWorkTrend/getProjectWorkTrendInfo.do")
    Observable<Data<ProjectWorkTrend>> getProjectWorkTrendInfo(@Field("pro_work_trend_id") long pro_work_trend_id);

//        // 获取项目动态详情
//    @FormUrlEncoded
//    @POST("projectWorkTrend/getProjectWorkTrendInfo.do")
//    Observable<Data<ProjectNews>> getProjectWorkTrendInfo2(@Field("pro_work_trend_id") long pro_work_trend_id);

    // 获取项目动态详情
    @FormUrlEncoded
    @POST("projectProblem/queryProjectProblemList.do")
    Observable<Page<Problem>> queryProjectProblemList(@FieldMap Map<String, String> map);

    // 协调问题
    @FormUrlEncoded
    @POST("projectProblem/submitProjectProblemResolve.do")
    Observable<Data> submitProjectProblemResolve(@FieldMap Map<String, String> map);

    // 预警列表
    @FormUrlEncoded
    @POST("projectAlarm/queryProjectAlarmList.do")
    Observable<Page<EarlyWarning>> queryProjectAlarmList(@Field("uda") boolean uda, @Field("page") int page);

    //获取项目动态
    @FormUrlEncoded
    @POST("projectWorkTrend/getAppNewest.do")
    Observable<Page<ProjectNews>> getAppNewest(@Field("uda") boolean uda, @Field("page") int page);

    //获取项目动态
    @FormUrlEncoded
    @POST("projectWorkTrend/getLeaderInstruction.do")
    Observable<Page<ProjectNews>> getLeaderInstruction(@Field("uda") boolean uda, @Field("page") int page);

    //点赞
    @FormUrlEncoded
    @POST("projectAdmire/new.do")
    Observable<Data> up(@Field("project_id") Long project_id, @Field("pro_plan_progress_id") Long pro_plan_progress_id);

    //点赞
    @FormUrlEncoded
    @POST("projectAdmire/queryProjectAdmireList.do")
    Observable<Page<Up>> queryProjectAdmireList(@Field("project_id") Long project_id, @Field("page") Integer page);

    //关注
    @FormUrlEncoded
    @POST("myConcernedProject/add.do")
    Observable<Data> addFavorite(@Field("project_id") Long project_id);

    //关注
    @FormUrlEncoded
    @POST("myConcernedProject/cancel.do")
    Observable<Data> cancelFavorite(@Field("project_id") Long project_id);

    // 发表新的工作批示、留言或回复
    @FormUrlEncoded
    @POST("projectInstruction/new.do")
    Observable<Data> addInstruction(@FieldMap Map<String, String> map);

    // 批示列表
    @FormUrlEncoded
    @POST("projectInstruction/getProTree.do")
    Observable<ProTree> getProTree(@Field("instruction_obj_id") Long instructionObjId,
                                   @Field("instruction_type") Long instruction_type, @Field("read_only") boolean read_only);

    // 批示列表
    @FormUrlEncoded
    @POST("projectProblem/queryProblemCoordinationList.do")
    Observable<Page<ProjectProblem>> queryProblemCoordinationList(@Field("project_problem_id") Long project_problem_id,
                                                                  @Field("page") int page);

    //填写新的项目进展汇报(日报)
    @Multipart
    @POST("projectWorkTrend/saveProjectWorkTrendInfo.do")
    Observable<Data> saveProjectWorkTrendInfo(@PartMap Map<String, RequestBody> map);

    // 新增
    @Multipart
    @POST("projectProgress/createProgress.do")
    Observable<Data> createProgress(@PartMap Map<String, RequestBody> map);

    @POST
    Observable<String> getContent(@Url String url);

    @POST("app/apkVersion.do")
    Observable<VersionUpdate> apkVersion();

    /**
     * 周汇报列表
     *
     * @param data
     * @return
     */
    @FormUrlEncoded
    @POST("projectWeeklyReport/getList.do")
    Observable<String> projectWeeklyReportList(@Field("list_photo") String data, @Field("_tk") String _tk);

    /**
     * 项目详情获取其他联系人
     *
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("projectBase/getOgazitionAll.do")
    Observable<Page<ProjectTimelineActivity.ProjectPerson>> getOgazitionAll(@Field("projectId") long projectId);

    @FormUrlEncoded
    @POST("projectWeeklyReport/getList.do")
    Observable<String> projectWeeklyReportList1();

    @FormUrlEncoded
    @POST("projectInstruction/delete.do")
    Observable<Data> projectInstructionDelete(@Field("delId") Long project_instruction_id);

    @FormUrlEncoded
    @POST("user/modifyPwd.do")
    Observable<Data> modifyPwd(@Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    @FormUrlEncoded
    @POST("liveMeeting/get.do")
    Observable<Data<ArrayList<MeetingRoom>>> liveMeetingGet(@Field("page") Integer page);

    @FormUrlEncoded
    @POST("projectProgress/queryProgressListForSupervise.do")
    Observable<Page<ProjectTimeline>> queryProgressListForSupervise(@Field("list_type") String list_type,
                                                                    @Field("page") Integer page, @Field("pcount") Boolean pcount);

    @FormUrlEncoded
    @POST("projectSupervise/querySuperviseListByProgress.do")
    Observable<Page<Management>> querySuperviseListByProgress(@Field("pro_plan_progress_id") Long pro_plan_progress_id,
                                                              @Field("page") Integer page, @Field("respOrgAll") Boolean respOrgAll);

    @FormUrlEncoded
    @POST("projectSupervise/saveProjectSupervise.do")
    Observable<Data> saveProjectSupervise(@FieldMap Map<String, String> map);

    /**
     * 获取周报计划列表
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("app/projectWeeklyPlan/getList.do")
    Observable<Page<WeekPlan>> projectWeeklyPlanList(@Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("app/projectWeeklyReport/getList.do")
    Observable<Page<WeekReport>> projectWeeklyReportList(@Field("device_id") String device_id);

    /**
     * 填写周计划时获取项目列表
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("app/projectWeeklyPlan/getProjectName.do")
    Observable<Page<Projects>> projectWeeklyPlanProjectName(@Field("device_id") String device_id);

    /**
     * 填写周计划提交
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("projectWeekPlan/save.do")
    Observable<Data> projectWeeklyPlanSave(@Field("device_id") String device_id, @Field("weeks") String weeks, @Field("projectId") String projectId,
                                           @Field("createTime") String creatTime, @Field("context") String context, @Field("weeklyPlanId") String weeklyPlanId);

    /**
     * 删除周计划
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("projectWeekPlan/deleteWeekplan.do")
    Observable<Data> projectWeekPlandelete(@Field("device_id") String device_id, @Field("delId") int id);

    /**
     * 填写周计划时获取项目列表
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("app/projectWeeklyReport/getProjectName.do")
    Observable<Page<Projects>> projectWeeklyReportProjectName(@Field("device_id") String device_id);

    /**
     * 填写周计划时获取项目列表 后获取 项目计划周次
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("app/projectWeeklyReport/getWeeksInfo.do")
    Observable<Page<WeeklyPlanBean>> projectWeeklyReportgetWeeksInfos(@Field("device_id") String device_id, @Field("projectId") int id);

    /**
     * 周报告提交&  保存
     *
     * @return
     */
    @Multipart
    @POST("projectWeekReport/save.do")
    Observable<Data> projectWeekReportSave(@PartMap Map<String, RequestBody> map);
//    Observable<Data> projectWeekReportSave(@Part("device_id") String device_id, @Part("context") String context, @Part("createTime") String createTime, @Part("projectId") String projectId,
//                                           @Part("weeklyPlanId") String weeklyPlanId, @Part("weeklyReportId") String weeklyReportId, @Part("weeks") String weeks, @Part("remark") String remark,
//                                           @Part("isSubmit") boolean isSubmit, @Part("uploadFileRemark") String uploadFileRemark, @PartMap Map<String, RequestBody> map);

    /**
     * 删除周报告
     *
     * @param device_id
     * @return
     */
    @FormUrlEncoded
    @POST("projectWeeklyReport/deleteWeekReport.do")
    Observable<Data> projectWeeklyReportDelete(@Field("device_id") String device_id, @Field("delId") int id);

    /**
     * 周报告审批列表
     *
     * @param device_id
     * @param projectName：项目名称（模糊查询）
     * @param resp_org_id：责任单位Id
     * @return
     */
    @FormUrlEncoded
    @POST("app/projectWeeklyReportReview/getList.do")
    Observable<Page<WeekChekModel>> projectWeeklyReportReviewList(@Field("device_id") String device_id, @Field("projectName") String projectName, @Field("resp_org_id") String resp_org_id);

    /**
     * @param weeklyReportId 周报告Id
     * @param projectId      项目id
     * @param auditRemark    审批备注
     * @param auditStatus    结果（2：同意，3：不同退回）
     * @return
     */
    @FormUrlEncoded
    @POST("projectWeeklyAudit/save.do")
    Observable<Data> projectWeeklyAuditSave(@Field("device_id") String device_id, @Field("weeklyReportId") String weeklyReportId, @Field("projectId") String projectId,
                                            @Field("auditRemark") String auditRemark, @Field("auditStatus") String auditStatus);


    /**
     * 大数据
     *
     * @return http://139.224.255.221:9090/ProjectBase/getFirstPageData.do
     */
    @FormUrlEncoded
    @POST("projectBase/getFirstPageData.do")
    Observable<Root<MapData<TotalByImportantType>>> firstPageData(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("projectBase/getFirstPageData.do")
    Observable<Root<MapData<TotalIndustryCategory>>> TotalIndustryCategory(@FieldMap HashMap<String, String> hashMap);

    /**
     * 环境监测
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/porjectEnvironmentalMonitoringDataAppController/getEarlyWarningList")
    Observable<ArrayList<Mooitoring>> getEarlyWarningList(@FieldMap HashMap<String, String> hashMap);

    /**
     * 获取预警详情
     *
     * @param id
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("/porjectEnvironmentalMonitoringDataAppController/getEarlyWarningDetail")
    Observable<ProjectBase<WarningDetails>> getEarlyWarningDetail(@Field("id") long id, @Field("project_id") long projectId);

    /**
     * 更新预警处理状态及发送短信
     *
     * @param id
     * @param isSendMsg
     * @return
     */
    @FormUrlEncoded
    @POST("/porjectEnvironmentalMonitoringDataAppController/update")
    Observable<String> update(@Field("id") long id, @Field("isSendMsg") boolean isSendMsg, @Field("phoneNumbers") String phoneNumbers);


}
