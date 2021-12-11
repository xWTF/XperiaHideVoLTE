package wtf.x.xposed.xperia_volte_remover;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class EventListener implements IXposedHookLoadPackage {
    @Override
    @SuppressWarnings("deprecation")
    public void handleLoadPackage(final LoadPackageParam aparam) throws Throwable {
        if (!"com.android.systemui".equals(aparam.packageName)) {
            return;
        }
        XposedHelpers.findAndHookMethod("com.android.systemui.statusbar.phone.StatusBarSignalPolicy", aparam.classLoader, "setMobileVolteIndicators", boolean.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = false;
            }
        });
    }
}
