## 목차
1. Introduce<br>
  1-1. Summary<br>
  1-2. Features<br>
  1-3. Delopment environment<br>
  1-4. Pacakge Structure<br>
2. Key code<br>
3. Trobule Shooting<br>
  3-1. 1st problem<br>
  3-2. 2nd problem<br>
4. Consider<br>
  4-1. User Experience

## 1. Introduce

### 1-1. Summary
깃허브 API를 통해 Repository를 조회하여 리스트로 보여주는 Application

### 1-2. Features
- `HTTP request`요청을 하여 데이터를 받음
- 받아온 데이터에서 `필요한 데이터를 추출`하여 리스트로 표시
- `페이징` 기능(= 무한스크롤)을 통해 리스트의 맨 마지막에 도달하면 다음 페이지를 로딩하여 표시
- 다음 페이지를 로딩하는 동안 마지막 Item의 위치에 `ProgressBar`를 띄움
- 재검색시 기존의 리스트를 제거하고, 검색결과에 따른 새로운 리스트 표시
- 초기 데이터 호출시 대기 시간동안 사용자가 시각적으로 로딩 중임을 알 수 있게함
- 결과에 따라 사용자에게 안내 토스트 메시지 출력
<img src="https://user-images.githubusercontent.com/76620764/148641919-6356652c-59d3-4b5f-a3f4-a7f8d47e99fc.gif" height="600"/>

### 1-3. Development environment
- 언어: Kotlin
- minSdkVersion: 23
- compileSDKVersion: 31
- 라이브러리: `Retrofit2`, `Glide`, `Lottie`
- jetpack: `Databinding`

### 1-4. Package Structure
```
📦 com.juhwan.github_search_project
 ┣ 📂 api
 ┃ ┗ 📜 RepoApi
 ┣ 📂 config
 ┃ ┗ 📜 ApplicationClass
 ┃ ┗ 📜 BaseActivity
 ┣ 📂 dto
 ┃ ┗ 📜 Item
 ┃ ┗ 📜 Owner
 ┃ ┗ 📜 RepoDto
 ┣ 📂 repository
 ┃ ┗ 📜 RepoRepository
 ┣ 📂 src
 ┃ ┗ 📜 MainActivity
 ┃ ┗ 📜 RepoAdapter
 ┣ 📂 util
 ┃ ┗ 📜 ImageBindingAdapter
 ┃ ┗ 📜 RetrofitCallback
 ┃ ┗ 📜 RetrofitUtil
```

## 2. Key Code
- 리스트의 최하단에 도달했는 지 감지하여 다음 페이지 request 
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

- 리스트의 마지막에 ProgressBar Item을 넣고 빼는 로직을 adapter에서 관리하여 시각적으로 로딩 중임을 제공
```
    fun loadMorePage (list: List<Item>, page: Int) {
        // 2페이지 이상 불러올때는 먼저 ProgressBar Item을 삭제한다.
        if(page > 1) {
            repoList.removeAt((page - 1) * 10)
            notifyItemRemoved((page - 1) * 10)
        }

        // 새로운 페이지 Item들을 넣고
        repoList.addAll(list)

        // 새로운 Item들이 10개라면
        if(list.size == PER_PAGE) {
            // ProgressBar를 위치할 비어있는 Item을 넣어주고
            repoList.add(null)
            notifyItemRangeInserted((page - 1) * 10, PER_PAGE + 1)
        }

        // 새로운 Item들이 10개 미만 이라면
        else {
            notifyItemRangeInserted((page - 1) * 10, list.size)
        }
    }
```

- BindingAdapter와 Glide를 통해 이미지 설정
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

## 3. Trobule Shooting

### 3-1. 1st trouble [(Issue #9)](https://github.com/juhwankim-dev/kotlin-github-search-project/issues/9) [(Resolve #10)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/10)
- 1번째 페이지에서 2번째 페이지로 넘어갈때는 정상적으로 작동
- 2번째 페이지부터 다음 페이지로 넘어갈때 `ProgressBar`를 띄우는 Item 위치에 avatar 이미지가 같이 뜸
<img src="https://user-images.githubusercontent.com/76620764/148577139-e8a26566-7e93-4b8b-9c3e-46e6667e6e2d.gif" height="600"/>

<br>

- [Glide 공식문서](http://bumptech.github.io/glide/doc/caching.html)에서 힌트를 얻어 마지막 Item이 보이기 전 `캐시`를 삭제해보았으나 여전히 문제가 발생
- `notifyItemRemoved`, `notifyItemRangeInserted`와 관련된 이슈일까 하여 조사해보았지만 문제 X
- Log를 찍어 해당 Item에 url이 들어가지 않는 것을 확실하게 확인
- url이 비어있으면 `흰색 배경`을 띄우는 대안으로 해결
<img src="https://user-images.githubusercontent.com/76620764/148633897-c0aa512b-2465-4e75-93c6-1799141dd23d.gif" height="600"/>

### 3-2. 2nd trouble [(Issue #12)](https://github.com/juhwankim-dev/kotlin-github-search-project/issues/12) [(Resolve #13)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/13)
- 최초 앱 실행 후 검색시 `10개`의 리스트가 정상적으로 요청 및 표시됨
- 이후 재검색시 리스트가 `20개`씩 요청 및 표시되는 현상이 일어남
<img src="https://user-images.githubusercontent.com/76620764/148635378-b1060cd8-fb34-4a4f-8e59-27b682b19fdd.gif" height="600"/>

<br>

- 재검색 시 리스트가 지워진 직후 Item의 개수가 0일 때 기존의 하단 감지 로직을 통과하는 현상을 발견함
- Item 개수가 0이라서 이미 하단에 도달한 것으로 인식했던 것이 원인
- 조건을 하나 더 추가함으로써 간단하게 해결
```
                if (lastVisibleItemPosition > 0 &&
                    !binding.rvRepo.canScrollVertically(1) &&
                    lastVisibleItemPosition == itemTotalCount - 1) {
                    selectAllRepos()
                }
```

## 4. Consider

### 4-1. User Experience
- 첫 번째 UX 고려사항 [(Resolves #15)](https://github.com/juhwankim-dev/kotlin-github-search-project/pull/17)
- HTTP request 요청 전에 Lottie를 띄우고 응답을 받으면 Lottie를 안보이게 설정
- `Lottie`를 사용함으로써 사용자가 대기하는 `체감 시간 감소` 효과

<br>

- 두 번째 UX 고려사항
- 단순히 `notifyDataSetChanged`를 사용하지 않고 `notifyItemRangeInserted`와 notifyItemRemoved`사용
- 필요한 부분만 업데이트함으로써 `성능 측면을 고려`
- 사용자 입장에서 화면이 깜빡이는 현상이 사라짐
