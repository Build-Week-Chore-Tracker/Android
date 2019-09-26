package com.lambdaschool.choretracker.database

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.lambdaschool.choretracker.util.DatabaseRepoInterface
import com.lambdaschool.choretracker.model.*
import com.lambdaschool.choretracker.util.prefs
import com.lambdaschool.choretracker.retrofit.ChoreTrackerAPI
import com.lambdaschool.choretracker.util.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatabaseRepo(val contxt: Context) : DatabaseRepoInterface {

    var retrofitInstance = ChoreTrackerAPI.Factory.create()
    val context = contxt.applicationContext

    override fun registerUser(creds: CredentialsAPI): LiveData<Boolean> {

        val registrationSuccessful = MutableLiveData<Boolean>()

        retrofitInstance.userRegistration(creds).enqueue(object: Callback<RegistrationReturnedAPI>{

            override fun onFailure(call: Call<RegistrationReturnedAPI>, t: Throwable) {
                registrationSuccessful.value = false
            }

            override fun onResponse(
                call: Call<RegistrationReturnedAPI>,
                response: Response<RegistrationReturnedAPI>
            ) {
                registrationSuccessful.value = true
            }
        })
        return registrationSuccessful
    }

    override fun loginUser(creds: CredentialsAPI): LiveData<Boolean> {

        val loginSuccessful = MutableLiveData<Boolean>()

        retrofitInstance.userLogin(creds).enqueue(object: Callback<LoginReturnedAPI> {
            override fun onFailure(call: Call<LoginReturnedAPI>, t: Throwable) {
                loginSuccessful.value = false
            }

            override fun onResponse(
                call: Call<LoginReturnedAPI>,
                response: Response<LoginReturnedAPI>
            ) {
                var token = ""
                response.body()?.token?.let {
                    token = it
                }

                var userId = -1
                response.body()?.user?.let {
                    userId = it
                }

                prefs.createLoginCredentialEntry(LoginReturnedAPI("", token, userId))

                loginSuccessful.value = true
            }

        })
        return loginSuccessful
    }

    // Table: chore_table
    override fun createChore(chore: Chore) {
        CreateChoreAsyncTask(database.databaseDao()).execute(chore)
    }

    override fun getAllChoresForParentId(parentId: Int): LiveData<List<Chore>> {
        return database.databaseDao().getAllChoresForParentId(parentId)
    }

    override fun getAllChoresForChildId(childId: Int): LiveData<List<Chore>> {
        return database.databaseDao().getAllChoresForChildId(childId)
    }

    override fun getAllChoresForParentIdExceptChildId(parentId: Int, childId: Int):
            LiveData<List<Chore>> {
        return database.databaseDao().getAllChoresForParentIdExceptChildId(parentId, childId)
    }

    override fun updateChore(chore: Chore) {
        UpdateChoreAsyncTask(database.databaseDao()).execute(chore)
    }

    override fun deleteChore(chore: Chore) {
        DeleteChoreAsyncTask(database.databaseDao()).execute(chore)
    }


    // Table: child_table
    override fun createChild(child: Child) {
        CreateChildAsyncTask(database.databaseDao()).execute(child)
    }

    override fun getAllChildForParentId(parentId: Int): LiveData<List<Child>> {
        return database.databaseDao().getAllChildForParentId(parentId)
    }

    override fun updateChild(child: Child) {
        UpdateChildAsyncTask(database.databaseDao()).execute(child)
    }

    override fun deleteChild(child: Child) {
        DeleteChildAsyncTask(database.databaseDao()).execute(child)
    }


    // Table: child_login_credential
    override fun createChildLoginCredential(childCreds: ChildLoginCredential) {
        CreateChildLoginCredentialAsyncTask(database.databaseDao()).execute(childCreds)
    }

    override fun getChildLoginCredentialForUsernamePassword(username: String, password: String):
            LiveData<ChildLoginCredential> {
        return database.databaseDao().getChildLoginCredentialForUsernamePassword(username, password)
    }

    override fun updateChildLoginCredential(childCreds: ChildLoginCredential) {
        UpdateChildLoginCredentialAsyncTask(database.databaseDao()).execute(childCreds)
    }

    override fun deleteChildLoginCredential(childCreds: ChildLoginCredential) {
        DeleteChildLoginCredentialAsyncTask(database.databaseDao()).execute(childCreds)
    }



    private val database by lazy {
        Room.databaseBuilder(
            context,
            Database::class.java,
            "chore_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    companion object {

        class CreateChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.createChore(it)
                }
            }
        }

        class UpdateChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.updateChore(it)
                }
            }
        }

        class DeleteChoreAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Chore, Unit, Unit>() {

            override fun doInBackground(vararg chore: Chore?) {
                chore[0]?.let {
                    dbDao.deleteChore(it)
                }
            }
        }

        class CreateChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.createChild(it)
                }
            }
        }

        class UpdateChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.updateChild(it)
                }
            }
        }

        class DeleteChildAsyncTask(val dbDao: DatabaseDAO) : AsyncTask<Child, Unit, Unit>() {

            override fun doInBackground(vararg child: Child?) {
                child[0]?.let {
                    dbDao.deleteChild(it)
                }
            }
        }

        class CreateChildLoginCredentialAsyncTask(val dbDao: DatabaseDAO) :
            AsyncTask<ChildLoginCredential, Unit, Unit>() {

            override fun doInBackground(vararg childCreds: ChildLoginCredential?) {
                childCreds[0]?.let {
                    dbDao.createChildLoginCredential(it)
                }
            }
        }

        class UpdateChildLoginCredentialAsyncTask(val dbDao: DatabaseDAO) :
            AsyncTask<ChildLoginCredential, Unit, Unit>() {

            override fun doInBackground(vararg childCreds: ChildLoginCredential?) {
                childCreds[0]?.let {
                    dbDao.updateChildLoginCredential(it)
                }
            }
        }

        class DeleteChildLoginCredentialAsyncTask(val dbDao: DatabaseDAO) :
            AsyncTask<ChildLoginCredential, Unit, Unit>() {

            override fun doInBackground(vararg childCreds: ChildLoginCredential?) {
                childCreds[0]?.let {
                    dbDao.deleteChildLoginCredential(it)
                }
            }
        }
    }
}