package fr.purplegiraffe.tvshows.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.purplegiraffe.tvshows.R
import fr.purplegiraffe.tvshows.data.Show
import fr.purplegiraffe.tvshows.data.ShowsManager
import kotlinx.android.synthetic.main.activity_show_list.*

open class ShowListActivity : AppCompatActivity(), ShowsManager.Client, ShowListAdapter.Delegate {


    private lateinit var adapter:ShowListAdapter
    protected lateinit var showsManager : ShowsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        this.adapter = ShowListAdapter(this)
        this.showsManager = ShowsManager(this)

        showListRecycler.layoutManager = LinearLayoutManager(this)
        showListRecycler.adapter = adapter
        showListRecycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }

    //ShowsManager.Client Interface
    override fun receiveShowList(showList: List<Show>) {
        adapter.reloadShowList(showList)
    }

    //ShowListAdapter.Delegate Interface
    override fun touchedShow(show: Show, index: Int) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("show",show)
        startActivity(intent)
    }
}
