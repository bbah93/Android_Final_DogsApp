# Android_Final_DogsApp
Android 4.4 Technical Mastery Final Dogs App
For some reason my recyclerview will not inflate for some reason and I don't know why. I usually am good with recyclerviews and the code doesn't seem like its wrong. Everythig else I felt fine with


########UPDATED README##############



Fixed everything and now app works perfectly.
Things I had to fix:
GET Call had wrong endpoint and included BaseURL, causing a onResponse with no data.
Null pointer for recycler in DogActivity fixed by setting adapter in RetrofitCall onResponse.
Fixed nullpointer in PhotoActivity by fixing typo in onBindViewHolder in DogAdapter. My putExtra said breedName and not breed (actual key name).
