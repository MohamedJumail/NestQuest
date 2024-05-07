package com.example.nestquest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ResultAdapter extends ArrayAdapter<UserProfile1> {

    private Context mContext;
    private int mResource;

    public ResultAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserProfile1> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        UserProfile1 userProfile1 = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        TextView textViewHousingType = convertView.findViewById(R.id.textViewHousingType);
        TextView textViewRent = convertView.findViewById(R.id.textViewRent);
        TextView textViewArea = convertView.findViewById(R.id.textViewArea);
        TextView textViewRooms = convertView.findViewById(R.id.textViewRooms);
        TextView textViewAddress = convertView.findViewById(R.id.textViewAddress); // Address TextView
        Button buttonCall = convertView.findViewById(R.id.buttonCall);

        textViewName.setText("Name: " + userProfile1.getFullName());
        textViewPhone.setText("Phone: " + userProfile1.getPhone());
        textViewHousingType.setText("Housing Type: " + userProfile1.getType());
        textViewRent.setText("Rent: " + userProfile1.getRent());
        textViewArea.setText("Area: " + userProfile1.getArea());
        textViewRooms.setText("Rooms: " + userProfile1.getRooms());
        textViewAddress.setText("Address: " + userProfile1.getAddress()); // Set address text

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                // Change text color of the button when clicked
                buttonCall.setTextColor(mContext.getResources().getColor(R.drawable.button_clicked));
                String phoneNumber = (String) v.getTag();
                initiateCall(phoneNumber);
            }

        });

        // Set phone number as a tag for the button
        buttonCall.setTag(userProfile1.getPhone());

        return convertView;
    }

    private void initiateCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        mContext.startActivity(intent);
    }
}
