package com.tyranotyrano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tyranotyrano.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
}
