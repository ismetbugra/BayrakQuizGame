package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSecenek:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar> //hashset içine yerlestirilen veriler rastgele karıstırılır
    private lateinit var vt:VeriTabaniYardimcisi

    private var soruSayac=0
    private var dogruSayac=0
    private var yanlisSayac=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt= VeriTabaniYardimcisi(this)
        val bayraklarDao=BayraklarDao()
        sorular=bayraklarDao.rastgele5BayrakGetir(vt)

        soruYukle()
        binding.buton1.setOnClickListener {
            dogruKontrol(binding.buton1)
            soruSayacKontrol()

        }
        binding.buton2.setOnClickListener {
            dogruKontrol(binding.buton2)
            soruSayacKontrol()

        }
        binding.buton3.setOnClickListener {
            dogruKontrol(binding.buton3)
            soruSayacKontrol()

        }
        binding.buton4.setOnClickListener {
            dogruKontrol(binding.buton4)
            soruSayacKontrol()

        }
    }

    fun soruYukle(){
        binding.textViewSoru.setText("${soruSayac+1}.Soru")

        dogruSecenek=sorular.get(soruSayac) // soruyu aldık alınan soru dogru secenek zaten
        binding.bayrakImageView.setImageResource(resources.getIdentifier(dogruSecenek.bayrak_resim,"drawable",packageName))
        yanlisSecenekler=BayraklarDao().rastgele3YanlisBayrakGetir(vt,dogruSecenek.bayrak_id) //rastgele 3 yanlıs şık getirdik

        tumSecenekler=HashSet()
        tumSecenekler.add(dogruSecenek)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        binding.buton1.text=tumSecenekler.elementAt(0).bayrak_ad
        binding.buton2.text=tumSecenekler.elementAt(1).bayrak_ad
        binding.buton3.text=tumSecenekler.elementAt(2).bayrak_ad
        binding.buton4.text=tumSecenekler.elementAt(3).bayrak_ad

    }

    fun soruSayacKontrol(){
        soruSayac++

        if(soruSayac!=5){
            soruYukle()
        }else{
            val intent=Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }

    }
    fun dogruKontrol(button:Button){
        val buttonYazi=button.text.toString()
        val dogruCevap=dogruSecenek.bayrak_ad

        if(buttonYazi==dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }
        binding.textViewDogru.text="Doğru:$dogruSayac"
        binding.textViewYanlis.text="Yanlış:$yanlisSayac"
    }
}