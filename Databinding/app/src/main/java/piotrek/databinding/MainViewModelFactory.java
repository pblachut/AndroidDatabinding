package piotrek.databinding;

public class MainViewModelFactory {
    public MainViewModel create(IMainView view){
        return new MainViewModel(view);
    }
}
