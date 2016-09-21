package com.fairphone.diagnostics.Module;

import android.content.Context;

import com.fairphone.diagnostics.R;
import com.fairphone.diagnostics.tests.Test;
import com.fairphone.diagnostics.tests.display.DisplayTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dirk on 20-10-15.
 */
public class DisplayModule implements Module {
    @Override
    public int getPictureResourceID() {
        return R.drawable.display_module;
    }

    @Override
    public int getDescriptionId() {
        return R.string.display_module_description;
    }

    @Override
    public int getModuleNameID() {
        return R.string.display_module_name;
    }


    public List<Test> getTestList(Context context) {
        List<Test> tests = new ArrayList<>();
        tests.add(new DisplayTest(context));
        return tests;
    }
}
