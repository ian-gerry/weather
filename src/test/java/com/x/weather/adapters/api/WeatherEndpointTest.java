package com.x.weather.adapters.api;

import com.x.weather.provider.openweather.OpenWeatherHttpDataSourcePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherEndpointTest {

    private final static String TEMPLATE  = "/api/v1/weather/location/{longitude}/{latitude}";
    private final static UriTemplate URI_TEMPLATE = new UriTemplate(TEMPLATE);
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {

        //TODO: Add fixture for OpenWeather response to have test stability
    }

    @Test
    public void shouldReturn200OKForValidLocation() throws Exception {

        URI uri = URI_TEMPLATE.expand("44.34","10.99");
        this.mockMvc.perform(get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dateUTC").isNotEmpty())
                .andExpect(jsonPath("$.metric").value("MAX_TEMP"))
        ;
    }

    @Test
    public void shouldReturn400ForMissingGeoCoordinateInURL() throws Exception {

        this.mockMvc.perform(get(URI_TEMPLATE.expand("","1"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        this.mockMvc.perform(get(URI_TEMPLATE.expand("1",""))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        this.mockMvc.perform(get(URI_TEMPLATE.expand("",""))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }



    private String dummyOpenWeatherApiResponse(){
        return
                "{\n" +
                        "    \"cod\": \"200\",\n" +
                        "    \"message\": 0,\n" +
                        "    \"cnt\": 5,\n" +
                        "    \"list\": [\n" +
                        "        {\n" +
                        "            \"dt\": 1698235200,\n" +
                        "            \"main\": {\n" +
                        "                \"temp\": 287.88,\n" +
                        "                \"feels_like\": 287.24,\n" +
                        "                \"temp_min\": 286.67,\n" +
                        "                \"temp_max\": 287.88,\n" +
                        "                \"pressure\": 1005,\n" +
                        "                \"sea_level\": 1005,\n" +
                        "                \"grnd_level\": 920,\n" +
                        "                \"humidity\": 70,\n" +
                        "                \"temp_kf\": 1.21\n" +
                        "            },\n" +
                        "            \"weather\": [\n" +
                        "                {\n" +
                        "                    \"id\": 500,\n" +
                        "                    \"main\": \"Rain\",\n" +
                        "                    \"description\": \"light rain\",\n" +
                        "                    \"icon\": \"10d\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"clouds\": {\n" +
                        "                \"all\": 100\n" +
                        "            },\n" +
                        "            \"wind\": {\n" +
                        "                \"speed\": 1.56,\n" +
                        "                \"deg\": 216,\n" +
                        "                \"gust\": 3.83\n" +
                        "            },\n" +
                        "            \"visibility\": 10000,\n" +
                        "            \"pop\": 0.49,\n" +
                        "            \"rain\": {\n" +
                        "                \"3h\": 0.33\n" +
                        "            },\n" +
                        "            \"sys\": {\n" +
                        "                \"pod\": \"d\"\n" +
                        "            },\n" +
                        "            \"dt_txt\": \"2023-10-25 12:00:00\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"dt\": 1698246000,\n" +
                        "            \"main\": {\n" +
                        "                \"temp\": 287.5,\n" +
                        "                \"feels_like\": 286.92,\n" +
                        "                \"temp_min\": 286.75,\n" +
                        "                \"temp_max\": 287.5,\n" +
                        "                \"pressure\": 1004,\n" +
                        "                \"sea_level\": 1004,\n" +
                        "                \"grnd_level\": 919,\n" +
                        "                \"humidity\": 74,\n" +
                        "                \"temp_kf\": 0.75\n" +
                        "            },\n" +
                        "            \"weather\": [\n" +
                        "                {\n" +
                        "                    \"id\": 500,\n" +
                        "                    \"main\": \"Rain\",\n" +
                        "                    \"description\": \"light rain\",\n" +
                        "                    \"icon\": \"10d\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"clouds\": {\n" +
                        "                \"all\": 92\n" +
                        "            },\n" +
                        "            \"wind\": {\n" +
                        "                \"speed\": 2.53,\n" +
                        "                \"deg\": 198,\n" +
                        "                \"gust\": 5.85\n" +
                        "            },\n" +
                        "            \"visibility\": 10000,\n" +
                        "            \"pop\": 0.62,\n" +
                        "            \"rain\": {\n" +
                        "                \"3h\": 1.51\n" +
                        "            },\n" +
                        "            \"sys\": {\n" +
                        "                \"pod\": \"d\"\n" +
                        "            },\n" +
                        "            \"dt_txt\": \"2023-10-25 15:00:00\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"dt\": 1698256800,\n" +
                        "            \"main\": {\n" +
                        "                \"temp\": 285.33,\n" +
                        "                \"feels_like\": 284.74,\n" +
                        "                \"temp_min\": 284.06,\n" +
                        "                \"temp_max\": 285.33,\n" +
                        "                \"pressure\": 1004,\n" +
                        "                \"sea_level\": 1004,\n" +
                        "                \"grnd_level\": 918,\n" +
                        "                \"humidity\": 82,\n" +
                        "                \"temp_kf\": 1.27\n" +
                        "            },\n" +
                        "            \"weather\": [\n" +
                        "                {\n" +
                        "                    \"id\": 803,\n" +
                        "                    \"main\": \"Clouds\",\n" +
                        "                    \"description\": \"broken clouds\",\n" +
                        "                    \"icon\": \"04n\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"clouds\": {\n" +
                        "                \"all\": 70\n" +
                        "            },\n" +
                        "            \"wind\": {\n" +
                        "                \"speed\": 3.04,\n" +
                        "                \"deg\": 201,\n" +
                        "                \"gust\": 5.27\n" +
                        "            },\n" +
                        "            \"visibility\": 10000,\n" +
                        "            \"pop\": 0.53,\n" +
                        "            \"sys\": {\n" +
                        "                \"pod\": \"n\"\n" +
                        "            },\n" +
                        "            \"dt_txt\": \"2023-10-25 18:00:00\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"dt\": 1698267600,\n" +
                        "            \"main\": {\n" +
                        "                \"temp\": 284.86,\n" +
                        "                \"feels_like\": 284.33,\n" +
                        "                \"temp_min\": 284.86,\n" +
                        "                \"temp_max\": 284.86,\n" +
                        "                \"pressure\": 1002,\n" +
                        "                \"sea_level\": 1002,\n" +
                        "                \"grnd_level\": 918,\n" +
                        "                \"humidity\": 86,\n" +
                        "                \"temp_kf\": 0\n" +
                        "            },\n" +
                        "            \"weather\": [\n" +
                        "                {\n" +
                        "                    \"id\": 804,\n" +
                        "                    \"main\": \"Clouds\",\n" +
                        "                    \"description\": \"overcast clouds\",\n" +
                        "                    \"icon\": \"04n\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"clouds\": {\n" +
                        "                \"all\": 100\n" +
                        "            },\n" +
                        "            \"wind\": {\n" +
                        "                \"speed\": 2.93,\n" +
                        "                \"deg\": 196,\n" +
                        "                \"gust\": 5.71\n" +
                        "            },\n" +
                        "            \"visibility\": 10000,\n" +
                        "            \"pop\": 0.22,\n" +
                        "            \"sys\": {\n" +
                        "                \"pod\": \"n\"\n" +
                        "            },\n" +
                        "            \"dt_txt\": \"2023-10-25 21:00:00\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"dt\": 1698278400,\n" +
                        "            \"main\": {\n" +
                        "                \"temp\": 284.14,\n" +
                        "                \"feels_like\": 283.54,\n" +
                        "                \"temp_min\": 284.14,\n" +
                        "                \"temp_max\": 284.14,\n" +
                        "                \"pressure\": 1002,\n" +
                        "                \"sea_level\": 1002,\n" +
                        "                \"grnd_level\": 917,\n" +
                        "                \"humidity\": 86,\n" +
                        "                \"temp_kf\": 0\n" +
                        "            },\n" +
                        "            \"weather\": [\n" +
                        "                {\n" +
                        "                    \"id\": 804,\n" +
                        "                    \"main\": \"Clouds\",\n" +
                        "                    \"description\": \"overcast clouds\",\n" +
                        "                    \"icon\": \"04n\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"clouds\": {\n" +
                        "                \"all\": 91\n" +
                        "            },\n" +
                        "            \"wind\": {\n" +
                        "                \"speed\": 2.04,\n" +
                        "                \"deg\": 230,\n" +
                        "                \"gust\": 3.68\n" +
                        "            },\n" +
                        "            \"visibility\": 10000,\n" +
                        "            \"pop\": 0.31,\n" +
                        "            \"sys\": {\n" +
                        "                \"pod\": \"n\"\n" +
                        "            },\n" +
                        "            \"dt_txt\": \"2023-10-26 00:00:00\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"city\": {\n" +
                        "        \"id\": 3163858,\n" +
                        "        \"name\": \"Zocca\",\n" +
                        "        \"coord\": {\n" +
                        "            \"lat\": 44.34,\n" +
                        "            \"lon\": 10.99\n" +
                        "        },\n" +
                        "        \"country\": \"IT\",\n" +
                        "        \"population\": 4593,\n" +
                        "        \"timezone\": 7200,\n" +
                        "        \"sunrise\": 1698212583,\n" +
                        "        \"sunset\": 1698250620\n" +
                        "    }\n" +
                        "}";
    }
}