package piotrek.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import piotrek.databinding.databinding.MainViewBinding;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModel viewModel = new MainViewModel();

        MainViewBinding binding = DataBindingUtil.setContentView(this, R.layout.main_view);
        binding.setViewModel(viewModel);
    }
}
