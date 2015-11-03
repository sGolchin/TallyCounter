// Generated code from Butter Knife. Do not modify!
package com.example.sarvenaz.tallycounter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.sarvenaz.tallycounter.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558508, "field 'counterView'");
    target.counterView = finder.castView(view, 2131558508, "field 'counterView'");
    view = finder.findRequiredView(source, 2131558510, "field 'btMinus' and method 'btMinus'");
    target.btMinus = finder.castView(view, 2131558510, "field 'btMinus'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.btMinus();
        }
      });
    view = finder.findRequiredView(source, 2131558509, "field 'btPlus' and method 'btPlus'");
    target.btPlus = finder.castView(view, 2131558509, "field 'btPlus'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.btPlus();
        }
      });
    view = finder.findRequiredView(source, 2131558505, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558505, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.counterView = null;
    target.btMinus = null;
    target.btPlus = null;
    target.toolbar = null;
  }
}
