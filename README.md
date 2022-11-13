# movie-backend
동영상플레이어 코멘트 백엔드

## API
* GET /videos/{movie_name}/comments
```
* request: none
* response: 
[
    {
        "id": 0,
        "author": "kihun.um",
        "startTime": 11,
        "endTime": 20,
        "comment": "test",
        "resolved": false
    },
    
    {
        "id": 1,
        "author": "kihun.um",
        "startTime": 99,
        "endTime": 120,
        "comment": "test2",
        "resolved": false
    }
]
```

* POST /videos/{movie_name}/comments
```
*request: (APPLICATION/JSON)
{
    "author": "kihun.um",
    "startTime": 11,
    "endTime": 20,
    "comment": "testfinal2",
    "resolved": false
}

* response: 
{
    "id": null,
    "author": "kihun.um",
    "startTime": 11,
    "endTime": 20,
    "comment": "testfinal2",
    "resolved": false
}
```

* PUT /videos/{movie_name}/comments/{comment_id}

```
*request: (APPLICATION/JSON)
{
    "author": "kihun.um",
    "startTime": 11,
    "endTime": 20,
    "comment": "testfinal2",
    "resolved": true
}

* response: 
{
    "id": null,
    "author": "kihun.um",
    "startTime": 11,
    "endTime": 20,
    "comment": "testfinal2",
    "resolved": true
}
```
