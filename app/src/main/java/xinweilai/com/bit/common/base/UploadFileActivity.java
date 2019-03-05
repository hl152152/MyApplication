package xinweilai.com.bit.common.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/4.
 */

public abstract class UploadFileActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    public ArrayList<String> picPaths = new ArrayList<>();

    private PhotoAdapter adapter;

    @Override
    protected void initUiAndListener() {
        GridView photoGv = getPhotoGv();
        picPaths.add(null);
        adapter = new PhotoAdapter(getActivity(), picPaths);
        photoGv.setAdapter(adapter);
        photoGv.setOnItemClickListener(this);
    }

    protected abstract GridView getPhotoGv();


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = picPaths.get(position);

        if (TextUtils.isEmpty(path)) {
            //
            PhotoPickerIntent intent = new PhotoPickerIntent(getActivity());
            intent.setSelectModel(SelectModel.MULTI);
            intent.setShowCarema(true); // 是否显示拍照， 默认false
            intent.setMaxTotal(Constance.MAX_PIC); // 最多选择照片数量，默认为9
            ArrayList<String> paths = getPhotoPaths();
            intent.setSelectedPaths(paths); // 已选中的照片地址， 用于回显选中状态
            // intent.setImageConfig(config);
            startActivityForResult(intent, Constance.REQUEST_CAMERA_CODE);
        } else {
            PhotoPreviewIntent intent = new PhotoPreviewIntent(getActivity());
            intent.setCurrentItem(position - 1); // 当前选中照片的下标
            ArrayList<String> paths = getPhotoPaths();
            intent.setPhotoPaths(paths); // 已选中的照片地址
            startActivityForResult(intent, Constance.REQUEST_PREVIEW_CODE);
        }
    }

    @NonNull
    public ArrayList<String> getPhotoPaths() {
        ArrayList<String> paths = new ArrayList<>();
        paths.addAll(picPaths);
        paths.remove(0);
        return paths;
    }

    @NonNull
    public ArrayList<File> getPhotoFiles() {
        ArrayList<File> files = new ArrayList<>();
        ArrayList<String> photoPaths = getPhotoPaths();

        for (String photoPath : photoPaths) {
            File file = CompressHelper.getDefault(CommonUtil.getContext())
                    .compressToFile(new File(photoPath));
            files.add(file);
        }
        return files;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case Constance.REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
//                // 拍照
//                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
//                    if(captureManager.getCurrentPhotoPath() != null) {
//                        captureManager.galleryAddPic();
//                        // 照片地址
//                        String imagePaht = captureManager.getCurrentPhotoPath();
//                        // ...
//                    }
//                    break;
                // 预览
                case Constance.REQUEST_PREVIEW_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
//            }
            }
        }
    }

    public PhotoAdapter getAdapter() {
        return adapter;
    }

    protected void refreshAdpater(ArrayList<String> paths) {
        // 处理返回照片地址
        picPaths.clear();
        picPaths.add(null);
        picPaths.addAll(paths);
        adapter.notifyDataSetChanged();
    }

}
