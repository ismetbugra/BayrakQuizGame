package com.example.bayrakquizuygulamasi

class BayraklarDao {

    fun rastgele5BayrakGetir(vt:VeriTabaniYardimcisi):ArrayList<Bayraklar>{
        var bayraklarListe=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val c= db.rawQuery("SELECT*FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (c.moveToNext()){
            val id=c.getColumnIndex("bayrak_id")
            val ad=c.getColumnIndex("bayrak_ad")
            val resim=c.getColumnIndex("bayrak_resim")
            var bayrak=Bayraklar(c.getInt(id),c.getString(ad),c.getString(resim))
            bayraklarListe.add(bayrak)
        }
        c.close()
        return bayraklarListe
    }

    fun rastgele3YanlisBayrakGetir(vt: VeriTabaniYardimcisi,bayrak_id:Int):ArrayList<Bayraklar>{
        var bayraklarListe=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val c= db.rawQuery("SELECT*FROM bayraklar WHERE bayrak_id!=$bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (c.moveToNext()){
            val id=c.getColumnIndex("bayrak_id")
            val ad=c.getColumnIndex("bayrak_ad")
            val resim=c.getColumnIndex("bayrak_resim")
            var bayrak=Bayraklar(c.getInt(id),c.getString(ad),c.getString(resim))
            bayraklarListe.add(bayrak)
        }
        c.close()
        return bayraklarListe
    }
}