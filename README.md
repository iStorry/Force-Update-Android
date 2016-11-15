[![Build Status](https://travis-ci.org/iStorry/fUpdate-Android.svg?branch=master)](https://travis-ci.org/iStorry/fUpdate-Android) [![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/green-coffee/blob/master/LICENSE.txt) [![Github](https://img.shields.io/badge/Github-iStorry-blue.svg)](https://github.com/iStorry) [![Blog](https://img.shields.io/badge/Blog-iStorry-orange.svg)](http://blog.istorry.com)


# fUpdate - Android
**fUpdate** is a library that allows you to force update your app.

## Example

```java
 private String url = "http://example.com/example.php";
 
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        /*
         * App Updater
         */
        Update update = new Update(url, Service.type.update, this);
        update.execute();
    }

 @Override
    public void getWebResponse(String result, Service.type type) {
        switch (type) {

            case update:
                try {
                    JSONObject object = new JSONObject();
                    int version = object.getInt("versioncode");
                    int versionCode = BuildConfig.VERSION_CODE;
                    if (version > versionCode){
                        Log.e("TAG", "Update Required");
                    } else {
                        Log.e("TAG", "Application is Up-to-date");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                break;

        }
    }
```
