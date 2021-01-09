package ru.vk.sladkiipirojok;

public class ServiceTestData {
    public static final String GIF_API_RESPONSE_BODY = "{\"data\":[\n" +
            "{\"type\":\"gif\",\"id\":\"EwWBtv774x7hK\",\n" +
            "\"url\":\"https://giphy.com/gifs/i-broken-broke-EwWBtv774x7hK\",\n" +
            "\"images\":{\n" +
            "\"original\":{\"height\":\"226\",\"width\":\"400\",\"size\":\"385539\",\"url\":\"https://media1.giphy.com/media/EwWBtv774x7hK/giphy.gif?cid=24e1d06dth0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&rid=giphy.gif\",\"mp4_size\":\"171012\",\"mp4\":\"https://media1.giphy.com/media/EwWBtv774x7hK/giphy.mp4?cid=24e1d06dth0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&rid=giphy.mp4\",\"webp_size\":\"152472\",\"webp\":\"https://media1.giphy.com/media/EwWBtv774x7hK/giphy.webp?cid=24e1d06dth0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&rid=giphy.webp\",\"frames\":\"13\",\"hash\":\"3b544b6f5768fb2ab1191a7775fa0759\"},\n" +
            "\"original_still\":{\"height\":\"226\",\"width\":\"400\",\"size\":\"40501\",\"url\":\"https://media1.giphy.com/media/EwWBtv774x7hK/giphy_s.gif?cid=24e1d06dth0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&rid=giphy_s.gif\"},\n" +
            "},\n" +
            "\"analytics_response_payload\":\"e=Z2lmX2lkPUV3V0J0djc3NHg3aEsmZXZlbnRfdHlwZT1HSUZfU0VBUkNIJmNpZD0yNGUxZDA2ZHRoMHhqaXh4bmt1Nmx5bmVmZWtjanB6YmkybmlyNXZmNmRvaWhnYTg\",\n" +
            "\"analytics\":{\"onload\":{\"url\":\"https://giphy-analytics.giphy.com/simple_analytics?response_id=th0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&event_type=GIF_SEARCH&gif_id=EwWBtv774x7hK&action_type=SEEN\"},\"onclick\":{\"url\":\"https://giphy-analytics.giphy.com/simple_analytics?response_id=th0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&event_type=GIF_SEARCH&gif_id=EwWBtv774x7hK&action_type=CLICK\"},\"onsent\":{\"url\":\"https://giphy-analytics.giphy.com/simple_analytics?response_id=th0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&event_type=GIF_SEARCH&gif_id=EwWBtv774x7hK&action_type=SENT\"}}}],\n" +
            "\"pagination\":{\"total_count\":4637,\"count\":1,\"offset\":79},\"meta\":{\"status\":200,\"msg\":\"OK\",\"response_id\":\"th0xjixxnku6lynefekcjpzbi2nir5vf6doihga8\"}}";
    public static final String MONEY_API_LATEST_BODY= "{\n" +
            "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "  \"license\": \"https://openexchangerates.org/license\",\n" +
            "  \"timestamp\": 1610128800,\n" +
            "  \"base\": \"USD\",\n" +
            "  \"rates\": {\n" +
            "    \"AED\": 3.673,\n" +
            "    \"ZMW\": 21.222624,\n" +
            "    \"ZWL\": 322\n" +
            "  }\n" +
            "}";
    public static final String MONEY_API_YESTERDAY_BODY ="{\n" +
            "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "  \"license\": \"https://openexchangerates.org/license\",\n" +
            "  \"timestamp\": 1610063999,\n" +
            "  \"base\": \"USD\",\n" +
            "  \"rates\": {\n" +
            "    \"AED\": 3.6732,\n" +
            "    \"ZMW\": 21.19513,\n" +
            "    \"ZWL\": 322\n" +
            "  }\n" +
            "}";
    public static final String MONEY_API_ALL_CURRENCY_BODY ="{\n" +
            "  \"AED\": \"United Arab Emirates Dirham\",\n" +
            "  \"ZMW\": \"Zambian Kwacha\",\n" +
            "  \"ZWL\": \"Zimbabwean Dollar\"\n" +
            "}";

    public static final String GIF_SERVICE_RESULT = "<img src=\"" + "https://media1.giphy.com/media/EwWBtv774x7hK/giphy.gif?cid=24e1d06dth0xjixxnku6lynefekcjpzbi2nir5vf6doihga8&rid=giphy.gif" + "\"/>";
}
