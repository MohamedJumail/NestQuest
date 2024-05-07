package com.example.nestquest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class ServiceProviderAdapter extends ArrayAdapter<ServiceProvider> {

    private Context mContext;
    private int mResource;

    public ServiceProviderAdapter(Context context, int resource, ArrayList<ServiceProvider> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        // Get current service provider
        ServiceProvider provider = getItem(position);

        // Populate TextViews with provider details
        TextView textViewFullName = listItemView.findViewById(R.id.textViewName);
        textViewFullName.setText("Name: " + provider.getFullName());

        TextView textViewCharge = listItemView.findViewById(R.id.textViewCharge);
        textViewCharge.setText("Charge: " + provider.getCharge());

        TextView textViewContactNumber = listItemView.findViewById(R.id.textViewContactNumber);
        textViewContactNumber.setText("Contact: " + provider.getContactNumber());

        // Button to initiate a phone call
        Button buttonCall = listItemView.findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = provider.getContactNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                mContext.startActivity(intent);
            }
        });

        Button buttonNotify = listItemView.findViewById(R.id.buttonnotify);
        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = provider.getContactNumber();
                String message = "There is a service request for you from NestQuest for " + provider.getServiceProvided() + " at " + provider.getAddress() + " by " + provider.getRequestedPersonName();
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                intent.putExtra("sms_body", message);
                mContext.startActivity(intent);

                // Show notification after sending SMS
                showNotification();
            }
        });

        return listItemView;
    }

    private void showNotification() {
        // Create an intent for opening the app when notification is clicked
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "channel_id")
                .setSmallIcon(R.drawable.img5)
                .setContentTitle("Service Requested")
                .setContentText("Service request sent successfully")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(1, builder.build());
    }
}
