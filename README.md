# movie-backend
동영상플레이어 코멘트 백엔드

## API
* GET /videos/{movie_name}/comments
** params: none
** response: 
```
[
    {
        "id": 0,
        "author": "kihun.um",
        "startTime": 11,
        "endTime": 20,
        "comment": "testfinal2",
        "resolved": false
    }
]
```
