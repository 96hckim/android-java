package com.hocheol.shoppinglist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hocheol.shoppinglist.db.AppDatabase;
import com.hocheol.shoppinglist.db.Items;

import java.util.List;

public class ShowItemListActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Items>> listOfItems;
    private AppDatabase appDatabase;

    public ShowItemListActivityViewModel(@NonNull Application application) {
        super(application);

        listOfItems = new MutableLiveData<>();
        appDatabase = AppDatabase.getDBInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Items>> getItemListObserver() {
        return listOfItems;
    }

    public void getAllItemList(int categoryId) {
        List<Items> itemList = appDatabase.shoppingListDao().getAllItemsList(categoryId);
        if (itemList.size() > 0) {
            listOfItems.postValue(itemList);
        } else {
            listOfItems.postValue(null);
        }
    }

    public void insertItems(Items item) {
        appDatabase.shoppingListDao().insertItems(item);
        getAllItemList(item.categoryId);
    }

    public void updateItems(Items item) {
        appDatabase.shoppingListDao().updateItems(item);
        getAllItemList(item.categoryId);
    }

    public void deleteItems(Items item) {
        appDatabase.shoppingListDao().deleteItem(item);
        getAllItemList(item.categoryId);
    }

}
