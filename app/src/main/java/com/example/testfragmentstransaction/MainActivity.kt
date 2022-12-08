package com.example.testfragmentstransaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import com.example.testfragmentstransaction.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        private enum class TransactionAction {
            ADD,
            REPLACE,
            REMOVE,
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            executeTransaction(TransactionAction.ADD)
        }
        findViewById<Button>(R.id.btnRemove).setOnClickListener {
            executeTransaction(TransactionAction.REMOVE)
        }
        findViewById<Button>(R.id.btnReplace).setOnClickListener {
            executeTransaction(TransactionAction.REPLACE)
        }
    }

    private fun executeTransaction(action: TransactionAction) {
        val container = R.id.container
        val transaction = supportFragmentManager.beginTransaction()
        when (action) {
            TransactionAction.ADD -> {
                transaction.add(
                    container,
                    MainFragment.newInstance(
                        supportFragmentManager.fragments.size
                    )
                )
            }
            TransactionAction.REPLACE -> {
                transaction.replace(
                    container,
                    MainFragment.newInstance(
                        supportFragmentManager.fragments.size
                    )
                )
            }
            TransactionAction.REMOVE -> {
                transaction.remove(supportFragmentManager.fragments.last())
            }
        }
        if (findViewById<CheckBox>(R.id.chbStack).isChecked) transaction.addToBackStack(null)
        transaction.commit()
    }
}
