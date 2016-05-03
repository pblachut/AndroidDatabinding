package piotrek.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import piotrek.databinding.databinding.MainViewBinding;

public class MainView extends AppCompatActivity implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainViewModel viewModel = new MainViewModelFactory().create(this);

        MainViewBinding binding = DataBindingUtil.setContentView(this, R.layout.main_view);
        binding.setViewModel(viewModel);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
