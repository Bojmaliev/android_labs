package mk.ukim.finki.students.lab_intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        Intent filterIntent = new Intent();
        filterIntent.setAction("android.intent.action.MAIN");

        filterIntent.addCategory("android.intent.category.LAUNCHER");

        TextView v = (TextView) findViewById(R.id.textView2);
        List<String> classes = getClassesForIntent(filterIntent);
        StringBuilder sb = new StringBuilder();
        for(String s: classes)sb.append(s).append("\n");
        v.setText(sb.toString());
    }

    public List<String> getClassesForIntent(Intent intent) {
        List<ResolveInfo> listActivities = getPackageManager().queryIntentActivities(intent, PackageManager.GET_META_DATA);
        List<String> classes = new ArrayList<>();

        for (ResolveInfo ri : listActivities)
            classes.add(ri.activityInfo.applicationInfo.className);
        return classes;

    }
}
