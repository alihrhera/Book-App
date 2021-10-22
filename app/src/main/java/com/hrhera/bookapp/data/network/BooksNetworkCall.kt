package com.hrhera.bookapp.data.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hrhera.bookapp.data.enums.AppStatus
import com.hrhera.bookapp.data.enums.UpdateStatus
import com.hrhera.bookapp.data.models.AppUser
import com.hrhera.bookapp.data.models.BookCategory
import com.hrhera.bookapp.data.models.OneBook
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import java.util.*


class BooksNetworkCall {

    suspend fun getAllBooks(): Map<String, Any> {
//        addBook()
        delay(1500)
        return  FirebaseFirestore.getInstance().collection("Books")
            .get().toDeferred()
    }

    suspend fun getTrendBooks(): Map<String, Any> {
        delay(1500)
        return  FirebaseFirestore.getInstance().collection("Books")
            .whereGreaterThan("likesTime", 7)
            .orderBy("likesTime", Query.Direction.DESCENDING)
            .get().toDeferred()
    }


    suspend fun getBooksByCategoryId(id: String): Map<String, Any> {
        return  FirebaseFirestore.getInstance().collection("Books")
            .whereEqualTo("categoryId",id)
            .get().toDeferred()

    }


    suspend fun getSliderBook(): List<OneBook> {

        delay(2000)
        return mutableListOf(
            OneBook(photo = "https://img.freepik.com/free-psd/business-stationery-mock-up-arrangement-cover-books_23-2148700184.jpg?size=626&ext=jpg"),
            OneBook(photo = "https://thumbs.dreamstime.com/b/poster-cover-book-design-template-space-photo-background-use-annual-report-proposal-portfolio-brochure-flyer-leaflet-153999686.jpg"),
            OneBook(photo = "https://selfpublishingadvice.org/wp-content/uploads/2020/09/ALLi-Final-30.jpg"),
            OneBook(photo = "https://image.freepik.com/free-vector/geometric-book-cover-flyer-designs-set_1017-18063.jpg"),
        )
    }


    private suspend fun Task<QuerySnapshot>.toDeferred(): Map<String, Any> {
        val deferred = CompletableDeferred<Map<String, Any>>()
        val map = mutableMapOf<String, Any>()
        this.addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                map["error"] = false
                val list = mutableListOf<OneBook>()
                for (document in it.result!!) {
                    list.add(document.toObject(OneBook::class.java))
                }

                map["data"] = list
                map["status"] = AppStatus.NORMAL
                deferred.complete(map)
            } else {
                map["error"] = true
                map["error_message"] = "No Data "
                map["data"] = arrayListOf<OneBook>()
                map["status"] = AppStatus.NORMAL
                deferred.complete(map)
            }
        }

        return deferred.await()
    }


    private suspend fun Task<QuerySnapshot>.toDeferredBookCategory(): List<BookCategory> {
        val deferred = CompletableDeferred<List<BookCategory>>()

        this.addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                val list = mutableListOf<BookCategory>()
                for (document in it.result!!) {
                    list.add(document.toObject(BookCategory::class.java))
                }
                deferred.complete(list)
            } else {
                deferred.complete(arrayListOf())
            }
        }
        return deferred.await()
    }


    suspend fun getBookCategory(): List<BookCategory> {
        return  FirebaseFirestore.getInstance().collection("BookCategory")
            .get().toDeferredBookCategory()
    }


    suspend fun updateBook(oneBook: OneBook): UpdateStatus {
        val deferred = CompletableDeferred<UpdateStatus>()

        FirebaseFirestore.getInstance().collection("Books").document(oneBook.id)
            .update(oneBook.toMap()).addOnCompleteListener {
                if (it.isSuccessful) {
                    deferred.complete(UpdateStatus.SUCCESS)

                } else {
                    deferred.complete(UpdateStatus.SOME_ERROR)
                    Log.e("Some Error ", "updateBook: ${it.exception?.message}")

                }

            }

        return deferred.await()
    }


    private suspend fun addBook(listOfCat: List<BookCategory>) {
        var time = Calendar.getInstance().timeInMillis
        val list = mutableListOf(
//            "https://cdn.flipsnack.com/landing/files/free-online-book-cover-maker",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSBloFQBVbSNh9Doj5oEpYxl_9UIyy7kHKYpyp-e4qs103DxGG7QC9eP1AKOPoAo8wCcU&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoI0BwXWQrZwSyvEx_AYITnrXsmqUMTbCjvbF-11CXCMsLMpcxkQhg5gxqUIr7ppg8lVU&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRYiCx4OSXwxurzmqSSjpHFPz0P7D3hUyvWb-Akp46FwfGihZKHxOnla-0Jl8XRz208GU&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRY2TqZA7lFnu0v6FbJnbajyV3GzNpSCXXqbw7PoI2HVD7cHYlBTIfYnDfLi7gvUVlHbS0&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStlEEgbbSMVdEc_tu7VpWAgwGiF4StIDnLd4_H2Ed_GAHOn9zbZgCv3IaloeJP0OojR_8&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLRhU8yy3aTwFrF7TS2T3r39dFhkKUrQ4xxQ-pqvrVd-j38a1Y-OjuIPmhLaDu9m2_cz4&usqp=CAU",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeoOqLzvvz6dSDAo_zMCXASHP7BgRVpR8vRMj7ZGvCiw0seafBhzfSWU4aFJ7KJnISzw4&usqp=CAU",
//            "https://ecolechezdonald.com/wp-content/uploads/2020/05/book-img2.jpg",

//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkdK8w335-t3zf-OwQNY9esM0NOtrE4UNyA&usqp=CAU",
//            "https://i.ytimg.com/vi/obdMfXTlyIM/maxresdefault.jpg",
//            "https://static.vecteezy.com/system/resources/thumbnails/002/110/581/small_2x/abstract-geometric-cover-design-free-vector.jpg",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5dGhop2FrtXxEEV2ILK4qVs5lungPY8V_4g&usqp=CAU",
//            "https://images-na.ssl-images-amazon.com/images/I/41UHen-IwaL._SX327_BO1,204,203,200_.jpg",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStCPJA0qw9z1h_7V352SWBVxwhKMuz-lK2-A&usqp=CAU",
//            "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/action-thriller-book-cover-design-template-3675ae3e3ac7ee095fc793ab61b812cc.jpg?ts=1588152105",
//            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdqYEuY4k2uyo2N0Kg2ebEvMW85sM3YpWunG-HlbprGs1tfkwweNOW-CH1whzu5LD8Y7o&usqp=CAU",
//
//            "https://click.af/images/detailed/68/M29-86-_2_.jpg",
//            "https://images-na.ssl-images-amazon.com/images/I/719qigFm50L.jpg",
//            "https://arabicvision.com/wp-content/uploads/2015/08/book2.jpg",
//            "https://binaries.templates.cdn.office.net/support/templates/en-us/lt22301254_quantized.png",
//            "https://www.jdandj.com/uploads/8/0/0/8/80083458/book-cover-designers-usa-for-award-winning-books-5_orig.jpg",
//            "https://www.jdandj.com/uploads/8/0/0/8/80083458/action-book-cover-designs-for-authors-5_orig.jpg",
//            "https://cdn.vox-cdn.com/thumbor/p-gGrwlaU4rLikEAgYhupMUhIJc=/0x0:1650x2475/1200x0/filters:focal(0x0:1650x2475):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/13757614/817BsplxI9L.jpg",
//            "https://coverworks-frequency.netdna-ssl.com/images/uploads/images/_constrain-480w/023-the-art-of-anarchy-coverworks-book-cover-design.jpg",
//
//            "https://www.glorify.com/wp-content/uploads/2020/12/90ff6f45058dae72c73a492ca8e62899.jpg",
//            "https://alltimedesign.com/wp-content/uploads/2020/11/ProfessionalCoverDesignFriesenPress.jpeg",
//            "http://www.baileydesignsbooks.com/wp-content/uploads/2020/05/gardenwall.jpg",
//            "https://arabicvision.com/wp-content/uploads/2015/08/book4_a.jpg",
//            "https://i.pinimg.com/originals/7f/eb/7e/7feb7e6280ab634142ac7bf95866e44a.jpg",
//            "https://image.freepik.com/free-psd/softcover-book-cover-mockup_337857-302.jpg",
//            "https://www.designforwriters.com/wp-content/uploads/2017/10/design-for-writers-book-cover-tf-2-a-million-to-one.jpg",
//            "https://inspgr.id/app/uploads/2019/01/design-adam-faniszlo-feature.jpg",


            // programming
            "https://images-na.ssl-images-amazon.com/images/I/41oBJp9leBL.jpg",
            "https://cdn.guru99.com/images/2/060520_1045_14BESTAlgor2.jpg",
            "https://www.cprogramming.com/books/cover_with_border_jumping_into_C++.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/819ribYV+LL.jpg",
            "https://rstudio-education.github.io/hopr/cover.png",
            "https://m.media-amazon.com/images/I/51fOBZJHdjL._SL500_.jpg",
            "https://learning-python.com/ora-pp4e-large.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/41HpPEcJ3XL._SX331_BO1,204,203,200_.jpg",
            "https://www.booksb2bportal.com/covers/31/9781683920908_L.jpg",
            "https://m.media-amazon.com/images/I/41gWxXyqdIL.jpg",

            )
        val nameList = listOf(
            "python programming",
            "intro to Algorithm ",
            "Jumping into C Plus plus",
            "Head First Java ",
            "Hands-On Programming With R",
            "Programming IOS 14 ",
            "python programming",
            "C Sharp Programming",
            "C Programming An Intro",
            "Learn android in 24 Hours",

            )


        val catList = mutableListOf<BookCategory>()
        for ((i, c) in nameList.withIndex()) {
            time += 1
            val oneBook = OneBook(
                phone = AppUser.getPhone(),
                photo = list[i],
                name = c,
                insertionLong = time,
                id = "$time",
                categoryId = "1634927860668"
            )

            FirebaseFirestore.getInstance().collection("Books").document(oneBook.id).set(oneBook)

            delay(1)
        }

    }

}