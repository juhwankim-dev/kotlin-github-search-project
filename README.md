## λͺ©μ°¨ π
1. μκ°<br>
  1-1. μμ½<br>
  1-2. κΈ°λ₯<br>
  1-3. κ°λ°νκ²½<br>
  1-4. ν¨ν€μ§ κ΅¬μ‘°<br>
2. ν΅μ¬ μ½λ<br>
3. λ¬Έμ  ν΄κ²°<br>
  3-1. μ²« λ²μ§Έ λ¬Έμ <br>
  3-2. λ λ²μ§Έ λ¬Έμ <br>
4. κ·Έ μΈ<br>
  4-1. κ³ λ €ν μ¬ν­<br>
  4-2. νμ€νΈ μ½λ

<br>

## 1. μκ° β€οΈ
### 1-1. μμ½
κΉνλΈ APIλ₯Ό ν΅ν΄ Repositoryλ₯Ό μ‘°ννμ¬ λ¦¬μ€νΈλ‘ λ³΄μ¬μ£Όλ Application

### 1-2. κΈ°λ₯
- `HTTP request`μμ²­μ νμ¬ λ°μ΄ν°λ₯Ό λ°μ
- λ°μμ¨ λ°μ΄ν°μμ `νμν λ°μ΄ν°λ₯Ό μΆμΆ`νμ¬ λ¦¬μ€νΈλ‘ νμ
- `νμ΄μ§` κΈ°λ₯(= λ¬΄νμ€ν¬λ‘€)μ ν΅ν΄ λ¦¬μ€νΈμ λ§¨ λ§μ§λ§μ λλ¬νλ©΄ λ€μ νμ΄μ§λ₯Ό λ‘λ©νμ¬ νμ
- λ€μ νμ΄μ§λ₯Ό λ‘λ©νλ λμ λ§μ§λ§ Itemμ μμΉμ `ProgressBar`λ₯Ό λμ
- μ¬κ²μμ κΈ°μ‘΄μ λ¦¬μ€νΈλ₯Ό μ κ±°νκ³ , κ²μκ²°κ³Όμ λ°λ₯Έ μλ‘μ΄ λ¦¬μ€νΈ νμ
- μ΄κΈ° λ°μ΄ν° νΈμΆμ λκΈ° μκ°λμ μ¬μ©μκ° μκ°μ μΌλ‘ λ‘λ© μ€μμ μ μ μκ²ν¨
- κ²°κ³Όμ λ°λΌ μ¬μ©μμκ² μλ΄ ν μ€νΈ λ©μμ§ μΆλ ₯
<img src="https://user-images.githubusercontent.com/76620764/148641919-6356652c-59d3-4b5f-a3f4-a7f8d47e99fc.gif" height="600"/>

### 1-3. κ°λ°νκ²½
- μΈμ΄: Kotlin
- minSdkVersion: 23
- compileSDKVersion: 31
- λΌμ΄λΈλ¬λ¦¬: `Retrofit2`, `Glide`, `Lottie`
- jetpack: `Databinding`

### 1-4. ν¨ν€μ§ κ΅¬μ‘°
- `μ μ§ λ³΄μ`λ₯Ό κ³ λ €νμ¬ ν¨ν€μ§ κ΅¬μ‘°λ₯Ό λλ
- Activity νμΌμ `μ€λ³΅λλ μ½λ`λ₯Ό μ€μ΄κΈ° μν΄ `BaseActivity` μμ± (νμ¬ νλ‘μ νΈμλ 1κ°μ Activityλ§ μ‘΄μ¬νμ§λ§ μ΄ν μν© κ³ λ €)
```
π¦ com.juhwan.github_search_project
 β£ π api
 β β π RepoApi
 β£ π config
 β β π ApplicationClass
 β β π BaseActivity
 β£ π dto
 β β π Item
 β β π Owner
 β β π RepoDto
 β£ π repository
 β β π RepoRepository
 β£ π src
 β β π MainActivity
 β β π RepoAdapter
 β£ π util
 β β π ImageBindingAdapter
 β β π RetrofitCallback
 β β π RetrofitUtil
```

### 1-5. μμ κ³Όμ 
- [Issue](https://github.com/juhwankim-dev/kotlin-github-search-project/issues?q=is%3Aissue+is%3Aclosed)
- [Pull Request](https://github.com/juhwankim-dev/kotlin-github-search-project/pulls?q=is%3Apr+is%3Aclosed)
- [Kanban board](https://github.com/juhwankim-dev/kotlin-github-search-project/projects/1)

<br>

## 2. ν΅μ¬ μ½λ π»
- λ¦¬μ€νΈμ μ΅νλ¨μ λλ¬νλ μ§ κ°μ§νμ¬ λ€μ νμ΄μ§ request 
- `lastVisibleItemPosition > 0`μ ν΅ν΄ μλ‘ κ²μμ request μμ²­μ΄ μ€λ³΅λλ μ΄μ ν΄κ²° [(Resolves #13)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/13)
- `!binding.rvRepo.canScrollVertically(1)`λ₯Ό ν΅ν΄ λ¦¬μ€νΈμ μ΅νλ¨μ λλ¬νλμ§ κ²μ¬
- `lastVisibleItemPosition == itemTotalCount - 1`λ₯Ό ν΅ν΄ λ§μ§λ§μΌλ‘ λ³΄μΈ μμ΄νμ΄ κ°μ₯ μλμ μλ μμ΄νκ³Ό μΌμΉνλ μ§ `2μ€ κ²μ¬`
```
        binding.rvRepo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount

                if (lastVisibleItemPosition > 0 &&
                    !binding.rvRepo.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount - 1) {
                    selectAllRepos()
                }
            }
        })
```

<br>

- λ¦¬μ€νΈμ λ§μ§λ§μ `ProgressBar Item`(=null item)μ λ£κ³  λΉΌλ λ‘μ§μ adapterμμ κ΄λ¦¬νμ¬ μκ°μ μΌλ‘ λ‘λ© μ€μμ μ κ³΅
- VIEW TYPEμ 2κ°λ‘ λλμ΄ ProgressBar νΉμ Repository μ λ³΄λ₯Ό λμ°λ λ°©λ²κ³Ό μ΄ λ°©λ² μ€ κ³ λ―ΌνμμΌλ μ μλ³΄λ€ νμκ° λ λ«λ€κ³  νλ¨
```
    fun loadMorePage (list: List<Item>, page: Int) {
        // 2νμ΄μ§ μ΄μ λΆλ¬μ¬λλ λ¨Όμ  ProgressBar Itemμ μ­μ νλ€.
        if(page > 1) {
            repoList.removeAt((page - 1) * 10)
            notifyItemRemoved((page - 1) * 10)
        }

        // μλ‘μ΄ νμ΄μ§ Itemλ€μ λ£κ³ 
        repoList.addAll(list)

        // μλ‘μ΄ Itemλ€μ΄ 10κ°λΌλ©΄
        if(list.size == PER_PAGE) {
            // ProgressBarλ₯Ό μμΉν  λΉμ΄μλ Itemμ λ£μ΄μ£Όκ³ 
            repoList.add(null)
            notifyItemRangeInserted((page - 1) * 10, PER_PAGE + 1)
        }

        // μλ‘μ΄ Itemλ€μ΄ 10κ° λ―Έλ§ μ΄λΌλ©΄
        else {
            notifyItemRangeInserted((page - 1) * 10, list.size)
        }
    }
```

<br>

- `BindingAdapter`μ `Glide`λ₯Ό ν΅ν΄ μ΄λ―Έμ§ μ€μ 
- μ²« λ²μ§Έ ifλ¬Έμ μμ±ν μ΄μ λ [λͺ©μ°¨ 3-1]μμ μ€λͺ
```
object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageByGlide")
    fun setImageByGlide(view: ImageView, url: String){
        if(url.isEmpty()) {
            Glide.with(view.context)
                .load("https://user-images.githubusercontent.com/76620764/148633216-6f17ddc8-9f1e-4666-b4a0-2c41e01a7314.png")
                .into(view)
        } else {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}
```

<br>

- `Databinding`μ μ΄μ©ν΄ λ·° κ΄λ ¨ λ‘μ§μ xmlλ‘ μ΄λ
- item `null check`μ λ°λΌ ProgressBarμ `visible` κ°μ μ€μ 
```
.
.
.
    <data>
        <import type="android.view.View"/>
        <variable
            name="item"
            type="com.juhwan.github_search_project.dto.Item" />
    </data>

.
.
.

        <ProgressBar
            android:id="@+id/progressBar"
            style="?android:attr/progressBarStyle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:visibility="@{item == null ? View.VISIBLE : View.GONE}"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
.
.
.
```


<br>

## 3. λ¬Έμ  ν΄κ²° π

### 3-1. μ²« λ²μ§Έ λ¬Έμ  [(Issue #9)](https://github.com/juhwankim-dev/kotlin-github-search-project/issues/9) [(Resolve #10)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/10)
- 1λ²μ§Έ νμ΄μ§μμ 2λ²μ§Έ νμ΄μ§λ‘ λμ΄κ°λλ μ μμ μΌλ‘ μλ
- 2λ²μ§Έ νμ΄μ§λΆν° λ€μ νμ΄μ§λ‘ λμ΄κ°λ `ProgressBar`λ₯Ό λμ°λ Item μμΉμ avatar μ΄λ―Έμ§κ° κ°μ΄ λΈ
<img src="https://user-images.githubusercontent.com/76620764/148577139-e8a26566-7e93-4b8b-9c3e-46e6667e6e2d.gif" height="600"/>

<br>

- [Glide κ³΅μλ¬Έμ](http://bumptech.github.io/glide/doc/caching.html)μμ ννΈλ₯Ό μ»μ΄ λ§μ§λ§ Itemμ΄ λ³΄μ΄κΈ° μ  `μΊμ`λ₯Ό μ­μ ν΄λ³΄μμΌλ μ¬μ ν λ¬Έμ κ° λ°μ
- `notifyItemRemoved`, `notifyItemRangeInserted`μ κ΄λ ¨λ μ΄μμΌκΉ νμ¬ μ‘°μ¬ν΄λ³΄μμ§λ§ λ¬Έμ  X
- Logλ₯Ό μ°μ΄ ν΄λΉ Itemμ urlμ΄ λ€μ΄κ°μ§ μλ κ²μ νμ€νκ² νμΈ
- urlμ΄ λΉμ΄μμΌλ©΄ `ν°μ λ°°κ²½`μ λμ°λ λμμΌλ‘ ν΄κ²°
<img src="https://user-images.githubusercontent.com/76620764/148633897-c0aa512b-2465-4e75-93c6-1799141dd23d.gif" height="600"/>

### 3-2. λ λ²μ§Έ λ¬Έμ  [(Issue #12)](https://github.com/juhwankim-dev/kotlin-github-search-project/issues/12) [(Resolve #13)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/13)
- μ΅μ΄ μ± μ€ν ν κ²μμ `10κ°`μ λ¦¬μ€νΈκ° μ μμ μΌλ‘ μμ²­ λ° νμλ¨
- μ΄ν μ¬κ²μμ λ¦¬μ€νΈκ° `20κ°`μ© μμ²­ λ° νμλλ νμμ΄ μΌμ΄λ¨
<img src="https://user-images.githubusercontent.com/76620764/148635378-b1060cd8-fb34-4a4f-8e59-27b682b19fdd.gif" height="600"/>

<br>

- μ¬κ²μ μ λ¦¬μ€νΈκ° μ§μμ§ μ§ν Itemμ κ°μκ° 0μΌ λ κΈ°μ‘΄μ νλ¨ κ°μ§ λ‘μ§μ ν΅κ³Όνλ νμμ λ°κ²¬ν¨
- Item κ°μκ° 0μ΄λΌμ μ΄λ―Έ νλ¨μ λλ¬ν κ²μΌλ‘ μΈμνλ κ²μ΄ μμΈ
- μ‘°κ±΄μ νλ λ μΆκ°ν¨μΌλ‘μ¨ κ°λ¨νκ² ν΄κ²°
```
                if (lastVisibleItemPosition > 0 &&
                    !binding.rvRepo.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount - 1) {
                    selectAllRepos()
                }
```


<br>

## 4. κ·Έ μΈ πΈ

### 4-1. κ³ λ €μ¬ν­
- μ²« λ²μ§Έ UX κ³ λ €μ¬ν­ [(Resolves #15)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/17)
- HTTP request μμ²­ μ μ Lottieλ₯Ό λμ°κ³  μλ΅μ λ°μΌλ©΄ Lottieλ₯Ό μλ³΄μ΄κ² μ€μ 
- νλ‘κ·Έλ¨μ΄ `μλ΅ μ€`μμ νμ
- `Lottie`λ₯Ό μ¬μ©ν¨μΌλ‘μ¨ μ¬μ©μκ° λκΈ°νλ `μ²΄κ° μκ° κ°μ` ν¨κ³Ό

<br>

- λ λ²μ§Έ UX κ³ λ €μ¬ν­
- λ¨μν `notifyDataSetChanged`λ₯Ό μ¬μ©νμ§ μκ³  `notifyItemRangeInserted`μ `notifyItemRemoved`μ¬μ©
- νμν λΆλΆλ§ μλ°μ΄νΈν¨μΌλ‘μ¨ `μ±λ₯ μΈ‘λ©΄μ κ³ λ €`
- μ¬μ©μ μμ₯μμ νλ©΄μ΄ κΉλΉ‘μ΄λ νμμ΄ μ¬λΌμ§

### 4-2. νμ€νΈ μ½λ
- [(Resolves #23)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/23)
- `Timeout`κ³Ό `Response Data`μ μΌμΉμ¬λΆ κ²μ¬
