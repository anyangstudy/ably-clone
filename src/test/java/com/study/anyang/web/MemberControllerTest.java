package com.study.anyang.web;

import com.study.anyang.common.BaseIntegrationTest;
import com.study.anyang.common.RestApiDocumentUtils;
import com.study.anyang.domain.Member;
import com.study.anyang.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends BaseIntegrationTest {

    @Autowired
    MemberRepository memberRepository;

    void register() throws Exception {
        //given
        Member member = new Member();
        member.setUserId("test");
        member.setUserName("test");
        member.setUserPw("1234");

        //when
        ResultActions resultActions = mvc.perform(
                RestDocumentationRequestBuilders.post("/user/register")
                .header("x-api-key", "API-KEY")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(member))
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().is3xxRedirection())
                //.andExpect(jsonPath("userId", is(notNullValue())))
                .andDo(
                        document("user/{method-name}",
                                RestApiDocumentUtils.getDocumentRequest(),
                                RestApiDocumentUtils.getDocumentResponse(),
                                requestHeaders(header()),
                                //pathParameters(pathParameter()),
                                responseFields(common())
                                    .andWithPrefix("data.", data())
                                    .andWithPrefix("error.", error())
                        )
                );
    }

    @Test
    void registerSuccess() {
    }

    @Test
    void list() throws Exception {
        //given
        Member member = new Member();
        member.setUserId("test");
        member.setUserName("test");
        member.setUserPw("1234");
        memberRepository.save(member);

        //when
        ResultActions resultActions = mvc.perform(get("/user/list")
                .with(user("test").password("1234").roles("ADMIN"))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk());
    }

    @Test
    void read() throws Exception {
        //given
        Member member = new Member();
        member.setUserId("test");
        member.setUserName("test");
        member.setUserPw("1234");
        memberRepository.save(member);

        //when
        ResultActions resultActions = mvc.perform(post("/users/{userId}", member.getUserId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().is3xxRedirection());
                //.andExpect(jsonPath("userName").value(member.getUserName()));
    }

    @Test
    void remove() {
    }

    @Test
    void modifyForm() {
    }

    @Test
    void modify() {
    }

    @Test
    void setupAdmin() {
    }

    @Test
    void setupAdminForm() {
    }

    @Override
    protected List<FieldDescriptor> error() {
        return Arrays.asList(
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답메세지")
        );
    }

    @Override
    protected List<FieldDescriptor> data() {
        return Arrays.asList(
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답메세지")
        );
    }

    @Override
    protected List<FieldDescriptor> common() {
        return Arrays.asList(
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답메세지"),
                subsectionWithPath("error").type(JsonFieldType.OBJECT).description("에러 데이터").optional(),
                subsectionWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터").optional()
        );
    }

    @Override
    protected List<HeaderDescriptor> header() {
        return Arrays.asList(headerWithName("x-api-key").description("Api Key"));
    }

    @Override
    protected ParameterDescriptor pathParameter() {
        return parameterWithName("userId").description("USER ID");
    }
}