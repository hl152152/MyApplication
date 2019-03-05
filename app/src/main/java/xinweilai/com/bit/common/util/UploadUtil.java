package xinweilai.com.bit.common.util;

import com.dunqi.gpm.chaotian.KongApp;
import com.dunqi.gpm.chaotian.common.api.ApiFactory;
import com.dunqi.gpm.chaotian.home.bean.Pic;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/30.
 */

public class UploadUtil {

    //返回图片上传的操作符，通过flatMap去使用
    public static Observable<String> upPic(ArrayList<String> pics) {

        ArrayList<File> newPhotos = new ArrayList<>();

        for (String photo : pics) {
            File newFile = CompressHelper.getDefault(KongApp.getContext())
                    .compressToFile(new File(photo));
            newPhotos.add(newFile);
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File newPhoto : newPhotos) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), newPhoto);
            builder.addFormDataPart("files", newPhoto.getName(), fileBody);
        }
        MultipartBody build = builder.build();

        return ApiFactory.getInstance()
                .upPic(build)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<ArrayList<Pic>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(ArrayList<Pic> pics) throws Exception {
                        ArrayList<String> strings = new ArrayList<>();
                        for (Pic pic : pics) {
                            strings.add(pic.getRemark());
                        }
                        String remarks = JsonUtils.toJson(strings);
                        return Observable.just(remarks);
                    }
                });


    }


}
