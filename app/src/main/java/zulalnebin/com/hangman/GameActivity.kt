package zulalnebin.com.hangman

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    var hiddenWord=""
    var secretWord=""
    var life:Int=7
    var letter:Char=' '
    var answer= arrayListOf<Char>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        createWord()


    }
    fun btnClick(view:View){
        when(view.id){
            R.id.btn_a->letter='A'
            R.id.btn_b->letter='B'
            R.id.btn_c->letter='C'
            R.id.btn_d->letter='D'
            R.id.btn_e->letter='E'
            R.id.btn_f->letter='F'
            R.id.btn_g->letter='G'
            R.id.btn_h->letter='H'
            R.id.btn_i->letter='I'
            R.id.btn_j->letter='J'
            R.id.btn_k->letter='K'
            R.id.btn_l->letter='L'
            R.id.btn_m->letter='M'
            R.id.btn_n->letter='N'
            R.id.btn_o->letter='O'
            R.id.btn_p->letter='P'
            R.id.btn_q->letter='Q'
            R.id.btn_r->letter='R'
            R.id.btn_s->letter='S'
            R.id.btn_t->letter='T'
            R.id.btn_u->letter='U'
            R.id.btn_v->letter='V'
            R.id.btn_w->letter='W'
            R.id.btn_x->letter='X'
            R.id.btn_y->letter='Y'
            R.id.btn_z->letter='Z'
        }
        //view.isEnabled()=false
        guess(letter)

    }
    fun createWord(){
        //tv_hiddenWord.setText("")
        life=7
        val words=resources.getStringArray(R.array.words)
        val random= Random
        secretWord=words[random.nextInt(words.size)]

        //kelimenin her harfini sembolle değiştirmek
        for(i in 0 until secretWord.length)
            hiddenWord+="●"
            tv_hiddenWord.setText(hiddenWord)
    }
    fun guess(letter:Char){

        if(secretWord.contains(letter.toString())){
            replace(letter)

        }
        else{
            life--
            val hangmanImage=findViewById<ImageView>(R.id.game_image)
            when (life) {
                7 -> hangmanImage!!.setImageResource(R.drawable.wrong_0)
                6 -> hangmanImage!!.setImageResource(R.drawable.wrong_1)
                5 -> hangmanImage!!.setImageResource(R.drawable.wrong_2)
                4 -> hangmanImage!!.setImageResource(R.drawable.wrong_3)
                3 -> hangmanImage!!.setImageResource(R.drawable.wrong_4)
                2 -> hangmanImage!!.setImageResource(R.drawable.wrong_5)
                1 -> hangmanImage!!.setImageResource(R.drawable.wrong_6)

            }
        }
        checkGameOver()
    }
    fun replace(letter: Char){
        for(i in 0 until secretWord.length){
            if(letter==secretWord[i]){
                var charArray=hiddenWord.toCharArray()
                charArray[i]=letter
                hiddenWord= String(charArray)
                answer.add(letter)
                //return letter
            }

        }

        tv_hiddenWord.setText(hiddenWord)
    }
    fun checkGameOver(){
        if(life==1){
            val builder = AlertDialog.Builder(this)

            // Set the alert dialog title
            builder.setTitle("OOPS! ")

            // Display a message on alert dialog
            builder.setMessage("BAŞARAMADIN...\n\nCevap:\n"+secretWord)

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("TEKRAR OYNA"){dialog, which ->
                // Do something when user press the positive button
                //Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
            // Display a negative button on alert dialog
            builder.setNegativeButton("ÇIKIŞ"){ dialogInterface: DialogInterface, i: Int ->
                finish()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
        if(!(life==1) && hiddenWord==secretWord ){
            val builder = AlertDialog.Builder(this)

            // Set the alert dialog title
            builder.setTitle("BRAVO!")

            // Display a message on alert dialog
            builder.setMessage("BAŞARDIN")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("TEKRAR OYNA"){dialog, which ->
                // Do something when user press the positive button
                //Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
            // Display a negative button on alert dialog
            builder.setNegativeButton("ÇIKIŞ"){ dialogInterface: DialogInterface, i: Int ->
                finish()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }

}
