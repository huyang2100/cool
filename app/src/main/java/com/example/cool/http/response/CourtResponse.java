package com.example.cool.http.response;

import com.example.cool.base.BaseResponse;

public class CourtResponse extends BaseResponse<CourtResponse.Court> {

    public static class Court {
        public int id;
        public String name;
        public String fyjc;
        public String jc;
        public int pid;
        public String time;
        public int order;

        @Override
        public String toString() {
            return "Court{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", fyjc='" + fyjc + '\'' +
                    ", jc='" + jc + '\'' +
                    ", pid=" + pid +
                    ", time='" + time + '\'' +
                    ", order=" + order +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CourtResponse{" +
                "data=" + data +
                '}';
    }
}
