package com.app.utlity;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.app.R;
import com.app.modal.user.Name;
import com.app.modal.user.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class BindingUtilClass {

    @BindingAdapter("app:name")
    public static void setName(TextView textView, Name name) {
        String nameUser = textView.getContext().getString(R.string.name, name.title, name.first, name.last);
        textView.setText(nameUser);
    }

    @BindingAdapter("app:gender_country")
    public static void setGenderNationality(TextView textView, User user) {
        String gender = textView.getContext().getString(R.string.gender, user.gender, user.nat);
        gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        textView.setText(gender);
    }

    @BindingAdapter("app:address")
    public static void setAddress(TextView textView, User user) {
        String address = getAddress(user);
        Spannable snapString = new SpannableString(address);
        snapString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), R.color.color_A259FF)), 0, user.location.street.number.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        snapString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, user.location.street.number.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int indexOfCountry = address.indexOf(user.location.country);
        int indexOfTimeZone = address.lastIndexOf(",") + 2;

        snapString.setSpan(new StyleSpan(Typeface.BOLD), indexOfCountry, indexOfCountry + user.location.country.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        snapString.setSpan(new StyleSpan(Typeface.ITALIC), indexOfTimeZone, address.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(snapString);
    }

    @BindingAdapter("app:gender")
    public static void setGender(TextView textView, String gender) {
        gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        textView.setText(gender);
    }

    @BindingAdapter("app:circle_image")
    public static void setCircleImage(ImageView image, String url) {
        Glide.with(image.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(image);

    }

    static String getAddress(User user) {
        String address = "";
        address += user.location.street.number + ", ";
        address += user.location.street.name + ", ";
        address += user.location.city + ", ";
        address += user.location.state + ", ";
        address += user.location.country + ", ";
        address += user.location.postcode + "\n";
        address += user.location.timezone.offset + ", ";
        address += user.location.timezone.description;

        return address;
    }

}
