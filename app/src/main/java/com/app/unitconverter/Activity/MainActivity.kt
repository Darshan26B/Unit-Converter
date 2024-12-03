package com.app.unitconverter.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.app.unitconverter.Adapter.unit_CAdapter
import com.app.unitconverter.Model.unitModel
import com.app.unitconverter.R
import com.app.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = ArrayList<unitModel>()
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var adapter: unit_CAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = unit_CAdapter(list)
        binding.UnitItemRCV.setHasFixedSize(true)
        binding.UnitItemRCV.layoutManager = GridLayoutManager(this, 2)
        binding.UnitItemRCV.adapter = adapter

         toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.share.setOnClickListener {
            // Create an intent to share content
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain" // Sharing text content
                putExtra(Intent.EXTRA_SUBJECT, "Check out this app!") // Optional: subject line
                putExtra(Intent.EXTRA_TEXT, "Hey, check out this cool app: <App URL or description>") // The content you want to share
            }

            // Start the sharing activity with a chooser
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        // Handle navigation drawer icon click
        binding.navIcon.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(binding.navigationView)) {
                binding.drawerLayout.closeDrawer(binding.navigationView)
            } else {
                binding.drawerLayout.openDrawer(binding.navigationView)
            }
        }

        // Handle navigation item clicks
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            val unitType = when (menuItem.itemId) {
                R.id.distance -> "Distance"
                R.id.volume -> "Volume"
                R.id.weight -> "Weight"
                R.id.temperature -> "Temperature"
                R.id.speed -> "Speed"
                R.id.energy -> "Energy"
                R.id.power -> "Power"
                R.id.time -> "Time"
                R.id.area -> "Area"
                R.id.storage -> "Storage"

                else -> null
            }

            unitType?.let {
                // Navigate to CommonActivity with the selected unit type
                val intent = Intent(this, CommonActivity::class.java)
                intent.putExtra("unitType", it)
                startActivity(intent)
            }

            binding.drawerLayout.closeDrawer(binding.navigationView)
            true
        }

        // Populate the list with unit models
        populateUnitList()

        // Set the item click listener for the RecyclerView adapter
        adapter.setOnItemClickListener(object : unit_CAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedUnit = list[position].UnitName
                val intent = Intent(this@MainActivity, CommonActivity::class.java)
                intent.putExtra("unitType", selectedUnit)
                startActivity(intent)
            }
        })
    }

    private fun populateUnitList() {
        list.apply {
            add(unitModel(R.drawable.distance, getString(R.string.distance)))
            add(unitModel(R.drawable.volume, getString(R.string.Volume)))
            add(unitModel(R.drawable.weight, getString(R.string.Weight)))
            add(unitModel(R.drawable.temperature, getString(R.string.Temperature)))
            add(unitModel(R.drawable.speed, getString(R.string.Speed)))
            add(unitModel(R.drawable.energy, getString(R.string.Energy)))
            add(unitModel(R.drawable.power, getString(R.string.Power)))
            add(unitModel(R.drawable.time, getString(R.string.Time)))
            add(unitModel(R.drawable.area, getString(R.string.Area)))
            add(unitModel(R.drawable.storage, getString(R.string.Storage)))
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(binding.navigationView)) {
            binding.drawerLayout.closeDrawer(binding.navigationView)
        } else {
            super.onBackPressed()
        }
    }
}
