package com.harris.ta

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.harris.ta.BuildConfig
import com.harris.ta.R
import com.harris.ta.DataItemModel
import com.harris.ta.ApiService
import com.harris.ta.RetroConfig
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profil.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("UNCHECKED_CAST")
class ProfilFragment : Fragment() {
    private var items: ArrayList<DataItemModel> = arrayListOf()
    private lateinit var npmTexView: TextView
    private lateinit var nik: TextView
    private lateinit var nisn: TextView
    private lateinit var tahunMasuk: TextView
    private lateinit var ivImageProfile: CircleImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        ivImageProfile = view.ivImageProfile
        npmTexView = view.tvNpm
        nik = view.tvNik
        tahunMasuk = view.tvTahun
        nisn = view.tvNISN
        npmTexView.text = "16670085"

        getData()

        return view;
    }

    private fun getData() {
        val apiService: ApiService = RetroConfig.provideApi()
        apiService.getProfil()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<DataItemModel>
                for (i: Int in items.indices) {
                    nik.text = items.get(i).nik
                    tahunMasuk.text = items.get(i).tahunMasuk
                    nisn.text = items.get(i).nisn
                    activity?.let { it1 ->
                        Glide.with(it1).load("http://informatika.upgris.ac.id/mobile/image/" + items.get(i).foto)
                            .override(512, 512).error(R.drawable.aa).into(ivImageProfile)
                    }
                }


            }, {
                error("Error" + it.message)
            })

    }


}