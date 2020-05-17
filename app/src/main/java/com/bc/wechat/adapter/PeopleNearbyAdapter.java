package com.bc.wechat.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bc.wechat.R;
import com.bc.wechat.entity.PeopleNearby;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 附近的人
 *
 * @author zhou
 */
public class PeopleNearbyAdapter extends ArrayAdapter<PeopleNearby> {
    List<PeopleNearby> mPeopleNearbyList;
    int mResource;
    private LayoutInflater mLayoutInflater;

    public PeopleNearbyAdapter(Context context, int resource, List<PeopleNearby> peopleNearbyList) {
        super(context, resource, peopleNearbyList);
        this.mResource = resource;
        this.mPeopleNearbyList = peopleNearbyList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(mResource, null);
            viewHolder = new ViewHolder();
            viewHolder.mAvatarSdv = convertView.findViewById(R.id.sdv_avatar);
            viewHolder.mNameTv = convertView.findViewById(R.id.tv_name);
            viewHolder.mWhatsupTv = convertView.findViewById(R.id.tv_whats_up);
            viewHolder.mDistanceTv = convertView.findViewById(R.id.tv_distance);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PeopleNearby peopleNearby = mPeopleNearbyList.get(position);
        if (!TextUtils.isEmpty(peopleNearby.getUserAvatar())) {
            viewHolder.mAvatarSdv.setImageURI(Uri.parse(peopleNearby.getUserAvatar()));
        }
        viewHolder.mNameTv.setText(peopleNearby.getUserNickName());
        viewHolder.mWhatsupTv.setText(peopleNearby.getUserSign());

        return convertView;
    }

    @Override
    public PeopleNearby getItem(int position) {
        return mPeopleNearbyList.get(position);
    }

    @Override
    public int getCount() {
        return mPeopleNearbyList.size();
    }

    public void setData(List<PeopleNearby> peopleNearbyList) {
        this.mPeopleNearbyList = peopleNearbyList;
    }

    class ViewHolder {
        // 头像
        SimpleDraweeView mAvatarSdv;
        // 姓名
        TextView mNameTv;
        // 签名
        TextView mWhatsupTv;
        // 距离信息
        TextView mDistanceTv;
    }

}
