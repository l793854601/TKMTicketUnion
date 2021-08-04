package com.example.tkmticketunion.model.domain;

import com.example.tkmticketunion.base.BaseModel;
import com.google.gson.annotations.SerializedName;

public class Ticket extends BaseModel {

    @SerializedName("tbk_tpwd_create_response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getTicketCode() {
        Response response = getResponse();
        if (response == null) {
            return null;
        }

        Response.Data data = response.getData();
        if (data == null) {
            return null;
        }

        String code = data.getModel();
        return code;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "response=" + response +
                '}';
    }

    public static class Response {
        @SerializedName("data")
        private Data data;
        @SerializedName("request_id")
        private String requestId;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "data=" + data +
                    ", requestId='" + requestId + '\'' +
                    '}';
        }

        public static class Data {
            @SerializedName("model")
            private String model;

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "model='" + model + '\'' +
                        '}';
            }
        }
    }
}
