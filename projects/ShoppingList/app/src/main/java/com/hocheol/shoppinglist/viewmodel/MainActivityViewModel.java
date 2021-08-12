package com.hocheol.shoppinglist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hocheol.shoppinglist.db.AppDatabase;
import com.hocheol.shoppinglist.db.Category;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Category>> listOfCategory;
    private AppDatabase appDatabase;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        listOfCategory = new MutableLiveData<>();
        appDatabase = AppDatabase.getDBInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Category>> getCategoryListObserver() {
        return listOfCategory;
    }

    public void getAllCategoryList() {
        List<Category> categoryList = appDatabase.shoppingListDao().getAllCategoriesList();
        if (categoryList.size() > 0) {
            listOfCategory.postValue(categoryList);
        } else {
            listOfCategory.postValue(null);
        }
    }

    public void insertCategory(String categoryName) {
        Category category = new Category();
        category.categoryName = categoryName;
        appDatabase.shoppingListDao().insertCategory(category);
        getAllCategoryList();
    }

    public void updateCategory(Category category) {
        appDatabase.shoppingListDao().updateCategory(category);
        getAllCategoryList();
    }

    public void deleteCategory(Category category) {
        appDatabase.shoppingListDao().deleteCategory(category);
        getAllCategoryList();
    }

}
