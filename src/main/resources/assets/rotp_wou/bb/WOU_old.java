public class WOU extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer headpiece;
	private final ModelRenderer eye_r1;
	private final ModelRenderer eye_r2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer hat;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer cube_r13;
	private final ModelRenderer cube_r14;
	private final ModelRenderer cube_r15;
	private final ModelRenderer cube_r16;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightUpperLeg;
	private final ModelRenderer rightLegJoint;
	private final ModelRenderer rightLowerLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftUpperLeg;
	private final ModelRenderer leftLegJoint;
	private final ModelRenderer leftLowerLeg;
	private final ModelRenderer rightArm;
	private final ModelRenderer shoulderR;
	private final ModelRenderer rightArmJoint;
	private final ModelRenderer rightForeArm;
	private final ModelRenderer stick;
	private final ModelRenderer cube_r17;
	private final ModelRenderer cube_r18;
	private final ModelRenderer leftArm;
	private final ModelRenderer shoulderL;
	private final ModelRenderer leftArmJoint;
	private final ModelRenderer leftForeArm;
	private final ModelRenderer torso;
	private final ModelRenderer booba_r1;
	private final ModelRenderer badges;
	private final ModelRenderer right_badge;
	private final ModelRenderer cube_r19;
	private final ModelRenderer cube_r20;
	private final ModelRenderer left_badge;
	private final ModelRenderer cube_r21;
	private final ModelRenderer cube_r22;
	private final ModelRenderer skarf;
	private final ModelRenderer cube_r23;
	private final ModelRenderer cube_r24;
	private final ModelRenderer cube_r25;
	private final ModelRenderer cube_r26;
	private final ModelRenderer cube_r27;
	private final ModelRenderer cube_r28;
	private final ModelRenderer cube_r29;
	private final ModelRenderer cube_r30;
	private final ModelRenderer cube_r31;
	private final ModelRenderer cube_r32;
	private final ModelRenderer cube_r33;
	private final ModelRenderer cube_r34;
	private final ModelRenderer cube_r35;
	private final ModelRenderer cube_r36;
	private final ModelRenderer cube_r37;
	private final ModelRenderer cube_r38;
	private final ModelRenderer cube_r39;
	private final ModelRenderer cube_r40;
	private final ModelRenderer scarfEdge2;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r41;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r42;
	private final ModelRenderer scarfEdge1;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r43;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r44;

	public WOU() {
		texWidth = 128;
		texHeight = 128;

		//  torso

		head = new ModelRenderer(this);
		head.setPos(0.0369F, -5.7074F, 0.6137F);
		head.texOffs(56, 82).addBox(-3.9574F, -2.7917F, -4.192F, 8.0F, 7.25F, 8.0F, 0.0F, true);

		headpiece = new ModelRenderer(this);
		headpiece.setPos(0.0426F, 0.8624F, -0.6276F);
		head.addChild(headpiece);
		headpiece.texOffs(15, 60).addBox(3.45F, -0.9041F, -0.6144F, 1.6F, 2.0F, 2.0F, 0.0F, true);
		headpiece.texOffs(15, 43).addBox(-5.05F, -0.9041F, -0.6144F, 1.6F, 2.0F, 2.0F, 0.0F, false);
		headpiece.texOffs(0, 61).addBox(3.52F, -3.6041F, -1.9644F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		headpiece.texOffs(0, 44).addBox(-4.52F, -3.6041F, -1.9644F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		headpiece.texOffs(108, 58).addBox(-2.0F, 2.3059F, -3.9644F, 4.0F, 1.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(66, 41).addBox(-3.3F, -0.0591F, 3.7856F, 6.6F, 0.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(66, 39).addBox(-3.3F, 0.7459F, 3.7856F, 6.6F, 0.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(66, 43).addBox(-3.3F, 1.2959F, 3.7856F, 6.6F, 0.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(66, 45).addBox(-3.3F, -0.8541F, 3.7856F, 6.6F, 0.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(66, 37).addBox(-3.3F, -1.7041F, 3.7856F, 6.6F, 0.25F, 1.0F, 0.0F, true);
		headpiece.texOffs(68, 74).addBox(-2.0F, -0.1041F, -3.7644F, 4.0F, 2.5F, 1.0F, 0.0F, true);

		eye_r1 = new ModelRenderer(this);
		eye_r1.setPos(0.0F, 4.3459F, 0.4356F);
		headpiece.addChild(eye_r1);
		setRotationAngle(eye_r1, 0.0F, -0.1484F, 0.0F);
		eye_r1.texOffs(6, 61).addBox(0.45F, -6.5F, -4.95F, 1.75F, 1.75F, 1.0F, 0.0F, true);

		eye_r2 = new ModelRenderer(this);
		eye_r2.setPos(0.0F, 4.3459F, 0.4356F);
		headpiece.addChild(eye_r2);
		setRotationAngle(eye_r2, 0.0F, 0.1484F, 0.0F);
		eye_r2.texOffs(7, 44).addBox(-2.2F, -6.5F, -4.95F, 1.75F, 1.75F, 1.0F, 0.0F, true);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.25F, 2.3459F, -5.1144F);
		headpiece.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.1833F, 0.0F);
		cube_r1.texOffs(12, 66).addBox(1.8F, -5.25F, 0.6F, 1.25F, 7.0F, 1.0F, 0.0F, true);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.25F, 2.3459F, -5.3144F);
		headpiece.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.3578F, 0.0F);
		cube_r2.texOffs(6, 65).addBox(3.05F, -6.0F, 0.3F, 1.5F, 8.5F, 1.0F, 0.0F, true);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.25F, 2.3459F, -5.3144F);
		headpiece.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, -1.405F, 0.0F);
		cube_r3.texOffs(0, 65).addBox(2.55F, -6.0F, -3.7F, 1.5F, 8.5F, 1.0F, 0.0F, true);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-0.5F, 0.8459F, -27.2644F);
		headpiece.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 1.5708F, 0.0F);
		cube_r4.texOffs(64, 51).addBox(-32.05F, -0.9F, 3.8F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r4.texOffs(64, 53).addBox(-32.05F, -2.55F, 3.8F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r4.texOffs(64, 57).addBox(-32.05F, -1.7F, 3.8F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r4.texOffs(64, 49).addBox(-32.05F, -0.1F, 3.8F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r4.texOffs(64, 59).addBox(-32.05F, 0.45F, 3.8F, 8.0F, 0.25F, 1.0F, 0.0F, true);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(-3.8F, -0.0791F, -24.2644F);
		headpiece.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, -1.5708F, 0.0F);
		cube_r5.texOffs(64, 61).addBox(21.05F, 0.025F, -0.5F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r5.texOffs(64, 65).addBox(21.05F, -1.625F, -0.5F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r5.texOffs(64, 55).addBox(21.05F, -0.775F, -0.5F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r5.texOffs(64, 63).addBox(21.05F, 0.825F, -0.5F, 8.0F, 0.25F, 1.0F, 0.0F, true);
		cube_r5.texOffs(64, 47).addBox(21.05F, 1.375F, -0.5F, 8.0F, 0.25F, 1.0F, 0.0F, true);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-0.25F, 2.3459F, -5.3144F);
		headpiece.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 1.405F, 0.0F);
		cube_r6.texOffs(0, 49).addBox(-4.05F, -6.0F, -3.7F, 1.5F, 8.5F, 1.0F, 0.0F, true);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(-0.25F, 2.3459F, -5.3144F);
		headpiece.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.3578F, 0.0F);
		cube_r7.texOffs(6, 49).addBox(-4.55F, -6.0F, 0.3F, 1.5F, 8.5F, 1.0F, 0.0F, true);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-0.25F, 2.3459F, -5.1144F);
		headpiece.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.1833F, 0.0F);
		cube_r8.texOffs(12, 50).addBox(-3.05F, -5.25F, 0.6F, 1.25F, 7.0F, 1.0F, 0.0F, true);

		hat = new ModelRenderer(this);
		hat.setPos(0.0532F, -2.6741F, -0.3652F);
		head.addChild(hat);
		hat.texOffs(0, 22).addBox(-4.6606F, -1.1175F, -4.4767F, 9.3F, 1.5F, 9.3F, 0.0F, true);
		hat.texOffs(0, 0).addBox(-5.4606F, 0.3816F, -5.2767F, 10.9F, 0.5F, 10.9F, 0.0F, true);
		hat.texOffs(62, 5).addBox(-4.5606F, -2.3675F, -4.3767F, 9.1F, 1.25F, 9.1F, 0.0F, true);
		hat.texOffs(34, 0).addBox(-4.4106F, -3.6175F, -4.2267F, 8.8F, 1.25F, 8.8F, 0.0F, true);
		hat.texOffs(26, 36).addBox(-3.6606F, -4.1175F, -3.4767F, 7.3F, 1.0F, 7.3F, 0.0F, true);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0894F, 0.6747F, 6.5237F);
		hat.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0873F, 0.0F, 0.0F);
		cube_r9.texOffs(23, 12).addBox(-5.5795F, -0.3931F, -0.95F, 11.0F, 0.5F, 1.9F, 0.0F, true);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-0.0106F, 0.5F, -6.3197F);
		hat.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.0873F, 0.0F, 0.0F);
		cube_r10.texOffs(63, 1).addBox(-5.4795F, -0.2184F, -1.075F, 11.0F, 0.5F, 2.15F, 0.0F, true);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(6.5156F, 0.4283F, 0.1733F);
		hat.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1745F);
		cube_r11.texOffs(55, 18).addBox(-1.075F, -0.25F, -5.45F, 2.15F, 0.5F, 10.9F, 0.0F, true);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-6.531F, 0.713F, 0.1733F);
		hat.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, 0.1745F);
		cube_r12.texOffs(29, 23).addBox(-1.075F, -0.4814F, -5.45F, 2.15F, 0.4F, 10.9F, 0.0F, true);

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(-6.1356F, 0.6325F, 6.2983F);
		hat.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0873F, 0.0F, 0.1745F);
		cube_r13.texOffs(46, 25).addBox(-0.475F, -0.45F, -0.675F, 1.15F, 0.5F, 1.15F, 0.0F, true);

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(6.1144F, 0.6325F, 6.2983F);
		hat.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0873F, 0.0F, -0.1745F);
		cube_r14.texOffs(46, 22).addBox(-0.675F, -0.4009F, -0.675F, 1.15F, 0.5F, 1.15F, 0.0F, true);

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(-6.1356F, 0.6325F, -5.9517F);
		hat.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.0873F, -0.0003F, 0.1745F);
		cube_r15.texOffs(52, 22).addBox(-0.475F, -0.4009F, -0.475F, 1.15F, 0.5F, 1.15F, 0.0F, true);

		cube_r16 = new ModelRenderer(this);
		cube_r16.setPos(6.1144F, 0.6325F, -5.9517F);
		hat.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.0873F, 0.0F, -0.1745F);
		cube_r16.texOffs(52, 25).addBox(-0.675F, -0.4209F, -0.475F, 1.15F, 0.5F, 1.15F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-1.9F, -12.0F, 0.0F);
		body.addChild(rightLeg);
		

		rightUpperLeg = new ModelRenderer(this);
		rightUpperLeg.setPos(1.8F, 12.0F, 0.0F);
		rightLeg.addChild(rightUpperLeg);
		rightUpperLeg.texOffs(0, 83).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		rightLegJoint = new ModelRenderer(this);
		rightLegJoint.setPos(0.0F, 6.0F, 0.0F);
		rightLeg.addChild(rightLegJoint);
		rightLegJoint.texOffs(2, 105).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

		rightLowerLeg = new ModelRenderer(this);
		rightLowerLeg.setPos(0.0F, 6.0F, 0.0F);
		rightLeg.addChild(rightLowerLeg);
		rightLowerLeg.texOffs(0, 94).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(1.9F, -12.0F, 0.0F);
		body.addChild(leftLeg);
		

		leftUpperLeg = new ModelRenderer(this);
		leftUpperLeg.setPos(-1.8F, 12.0F, 0.0F);
		leftLeg.addChild(leftUpperLeg);
		leftUpperLeg.texOffs(19, 83).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		leftLegJoint = new ModelRenderer(this);
		leftLegJoint.setPos(0.0F, 6.0F, 0.0F);
		leftLeg.addChild(leftLegJoint);
		leftLegJoint.texOffs(21, 105).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

		leftLowerLeg = new ModelRenderer(this);
		leftLowerLeg.setPos(0.0F, 6.0F, 0.0F);
		leftLeg.addChild(leftLowerLeg);
		leftLowerLeg.texOffs(19, 94).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-6.0F, -21.0F, 0.0F);
		body.addChild(rightArm);
		

		shoulderR = new ModelRenderer(this);
		shoulderR.setPos(0.0F, 0.0F, 0.0F);
		rightArm.addChild(shoulderR);
		shoulderR.texOffs(89, 111).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		rightArmJoint = new ModelRenderer(this);
		rightArmJoint.setPos(0.0F, 3.0F, 0.0F);
		rightArm.addChild(rightArmJoint);
		rightArmJoint.texOffs(69, 104).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

		rightForeArm = new ModelRenderer(this);
		rightForeArm.setPos(0.0F, 3.0F, 0.0F);
		rightArm.addChild(rightForeArm);
		rightForeArm.texOffs(93, 121).addBox(-2.25F, 3.0F, -2.25F, 4.5F, 2.0F, 4.5F, 0.0F, false);
		rightForeArm.texOffs(99, 100).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		stick = new ModelRenderer(this);
		stick.setPos(-0.75F, 4.1397F, 1.5613F);
		rightForeArm.addChild(stick);
		

		cube_r17 = new ModelRenderer(this);
		cube_r17.setPos(0.75F, -1.317F, -1.6651F);
		stick.addChild(cube_r17);
		setRotationAngle(cube_r17, 1.5708F, 0.0F, -1.5708F);
		cube_r17.texOffs(124, 112).addBox(-3.0F, -3.75F, -0.625F, 1.0F, 15.0F, 1.0F, 0.0F, false);

		cube_r18 = new ModelRenderer(this);
		cube_r18.setPos(0.75F, -1.317F, -2.6651F);
		stick.addChild(cube_r18);
		setRotationAngle(cube_r18, 1.5708F, 0.0F, -1.5708F);
		cube_r18.texOffs(112, 122).addBox(-3.0F, -3.75F, -0.625F, 1.0F, 1.0F, 4.5F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(5.9967F, -20.5648F, 0.093F);
		body.addChild(leftArm);
		

		shoulderL = new ModelRenderer(this);
		shoulderL.setPos(0.0F, 0.0F, 0.0F);
		leftArm.addChild(shoulderL);
		shoulderL.texOffs(107, 111).addBox(-1.9967F, -3.4352F, -2.093F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		leftArmJoint = new ModelRenderer(this);
		leftArmJoint.setPos(0.0F, 3.0F, 0.0F);
		leftArm.addChild(leftArmJoint);
		leftArmJoint.texOffs(116, 104).addBox(-0.9967F, -1.9352F, -1.593F, 3.0F, 3.0F, 3.0F, -0.1F, false);

		leftForeArm = new ModelRenderer(this);
		leftForeArm.setPos(0.0F, 3.0F, 0.0F);
		leftArm.addChild(leftForeArm);
		leftForeArm.texOffs(82, 100).addBox(-1.9967F, -0.4352F, -2.093F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		leftForeArm.texOffs(74, 121).addBox(-2.2467F, 2.5648F, -2.343F, 4.5F, 2.0F, 4.5F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setPos(0.0F, -13.0F, 0.0F);
		body.addChild(torso);
		torso.texOffs(17, 112).addBox(-4.0F, -11.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		booba_r1 = new ModelRenderer(this);
		booba_r1.setPos(0.0F, -10.0F, -1.75F);
		torso.addChild(booba_r1);
		setRotationAngle(booba_r1, -0.1309F, 0.0F, 0.0F);
		booba_r1.texOffs(43, 123).addBox(-4.0F, -0.85F, -0.4F, 8.0F, 4.0F, 1.0F, 0.0F, false);

		badges = new ModelRenderer(this);
		badges.setPos(0.0F, -11.0F, 0.0F);
		torso.addChild(badges);
		

		right_badge = new ModelRenderer(this);
		right_badge.setPos(1.875F, 8.125F, -2.125F);
		badges.addChild(right_badge);
		right_badge.texOffs(9, 117).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);
		right_badge.texOffs(10, 115).addBox(-0.625F, -0.625F, -0.225F, 1.25F, 1.25F, 0.25F, 0.0F, false);

		cube_r19 = new ModelRenderer(this);
		cube_r19.setPos(0.0F, 0.0F, 0.0F);
		right_badge.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 0.48F);
		cube_r19.texOffs(9, 121).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);

		cube_r20 = new ModelRenderer(this);
		cube_r20.setPos(0.0F, 0.0F, 0.0F);
		right_badge.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, -0.48F);
		cube_r20.texOffs(1, 125).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);

		left_badge = new ModelRenderer(this);
		left_badge.setPos(-1.875F, 8.125F, -2.125F);
		badges.addChild(left_badge);
		left_badge.texOffs(1, 117).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);
		left_badge.texOffs(5, 115).addBox(-0.625F, -0.625F, -0.225F, 1.25F, 1.25F, 0.25F, 0.0F, false);

		cube_r21 = new ModelRenderer(this);
		cube_r21.setPos(0.0F, 0.0F, 0.0F);
		left_badge.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, -0.48F);
		cube_r21.texOffs(1, 121).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);

		cube_r22 = new ModelRenderer(this);
		cube_r22.setPos(0.0F, 0.0F, 0.0F);
		left_badge.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.48F);
		cube_r22.texOffs(9, 125).addBox(-0.875F, -0.875F, -0.125F, 1.75F, 1.75F, 1.0F, 0.0F, false);

		skarf = new ModelRenderer(this);
		skarf.setPos(-0.0921F, -0.2327F, -0.5742F);
		badges.addChild(skarf);
		skarf.texOffs(118, 18).addBox(-1.0254F, -1.0352F, -5.1597F, 3.0F, 1.0F, 2.0F, 0.0F, true);

		cube_r23 = new ModelRenderer(this);
		cube_r23.setPos(5.3859F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r23);
		setRotationAngle(cube_r23, -1.2993F, 1.2956F, -1.1671F);
		cube_r23.texOffs(110, 22).addBox(-0.625F, 0.3007F, -0.4308F, 2.0F, 1.0F, 1.5F, 0.0F, true);

		cube_r24 = new ModelRenderer(this);
		cube_r24.setPos(-4.9368F, -0.6485F, -0.9224F);
		skarf.addChild(cube_r24);
		setRotationAngle(cube_r24, 1.1776F, 1.266F, 0.9673F);
		cube_r24.texOffs(119, 50).addBox(-1.875F, 0.0507F, -0.9692F, 3.5F, 1.0F, 1.0F, 0.0F, true);

		cube_r25 = new ModelRenderer(this);
		cube_r25.setPos(-4.9368F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r25);
		setRotationAngle(cube_r25, -1.2993F, -1.2956F, 1.1671F);
		cube_r25.texOffs(110, 18).addBox(-1.375F, 0.3007F, -0.4308F, 2.0F, 1.0F, 1.5F, 0.0F, true);

		cube_r26 = new ModelRenderer(this);
		cube_r26.setPos(-4.9368F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.9776F, -1.0054F, 0.822F);
		cube_r26.texOffs(106, 40).addBox(0.375F, 0.0507F, -0.7808F, 2.5F, 1.0F, 2.0F, 0.0F, true);

		cube_r27 = new ModelRenderer(this);
		cube_r27.setPos(-3.9368F, 0.3515F, -2.9224F);
		skarf.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.7798F, 0.6653F, 0.6947F);
		cube_r27.texOffs(120, 58).addBox(-1.875F, -1.3453F, -0.4315F, 3.0F, 1.5F, 1.0F, 0.0F, true);

		cube_r28 = new ModelRenderer(this);
		cube_r28.setPos(4.3859F, 0.3515F, -2.9224F);
		skarf.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.7798F, -0.6653F, -0.6947F);
		cube_r28.texOffs(120, 25).addBox(-1.125F, -1.3453F, -0.4315F, 3.0F, 1.5F, 1.0F, 0.0F, true);

		cube_r29 = new ModelRenderer(this);
		cube_r29.setPos(-3.9368F, 0.3515F, -2.9224F);
		skarf.addChild(cube_r29);
		setRotationAngle(cube_r29, 1.1113F, 0.5248F, 0.8169F);
		cube_r29.texOffs(108, 50).addBox(-1.625F, 0.3007F, -0.9692F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		cube_r30 = new ModelRenderer(this);
		cube_r30.setPos(4.3859F, 0.3515F, -2.9224F);
		skarf.addChild(cube_r30);
		setRotationAngle(cube_r30, 1.1113F, -0.5248F, -0.8169F);
		cube_r30.texOffs(120, 22).addBox(-1.375F, 0.3007F, -0.9692F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		cube_r31 = new ModelRenderer(this);
		cube_r31.setPos(-4.9368F, -0.6485F, -0.9224F);
		skarf.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.6998F, 1.3295F, 0.7432F);
		cube_r31.texOffs(107, 31).addBox(-1.875F, -1.3453F, -0.6815F, 3.5F, 1.5F, 1.5F, 0.0F, true);

		cube_r32 = new ModelRenderer(this);
		cube_r32.setPos(5.3859F, -0.6485F, -0.9224F);
		skarf.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.6998F, -1.3295F, -0.7432F);
		cube_r32.texOffs(105, 45).addBox(-1.625F, -1.3453F, -0.6815F, 3.5F, 1.5F, 1.5F, 0.0F, true);

		cube_r33 = new ModelRenderer(this);
		cube_r33.setPos(5.3859F, -0.6485F, -0.9224F);
		skarf.addChild(cube_r33);
		setRotationAngle(cube_r33, 1.1776F, -1.266F, -0.9673F);
		cube_r33.texOffs(119, 28).addBox(-1.625F, 0.0507F, -0.9692F, 3.5F, 1.0F, 1.0F, 0.0F, true);

		cube_r34 = new ModelRenderer(this);
		cube_r34.setPos(5.3859F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r34);
		setRotationAngle(cube_r34, -0.5527F, 1.3607F, -0.6155F);
		cube_r34.texOffs(117, 45).addBox(-1.875F, -1.3453F, -1.0685F, 3.5F, 1.5F, 2.0F, 0.0F, true);

		cube_r35 = new ModelRenderer(this);
		cube_r35.setPos(-4.9368F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r35);
		setRotationAngle(cube_r35, -0.5527F, -1.3607F, 0.6155F);
		cube_r35.texOffs(117, 40).addBox(-1.625F, -1.3453F, -1.0685F, 3.5F, 1.5F, 2.0F, 0.0F, true);

		cube_r36 = new ModelRenderer(this);
		cube_r36.setPos(5.3859F, -0.6485F, 2.1031F);
		skarf.addChild(cube_r36);
		setRotationAngle(cube_r36, -1.0136F, 0.9688F, -0.8651F);
		cube_r36.texOffs(119, 31).addBox(-2.875F, 0.0507F, -0.7808F, 2.5F, 1.0F, 2.0F, 0.0F, true);

		cube_r37 = new ModelRenderer(this);
		cube_r37.setPos(0.2246F, -0.5352F, 0.0903F);
		skarf.addChild(cube_r37);
		setRotationAngle(cube_r37, 2.7925F, 0.0F, 3.1416F);
		cube_r37.texOffs(108, 0).addBox(-4.0F, -1.5F, 3.0F, 8.0F, 2.0F, 2.0F, 0.0F, true);

		cube_r38 = new ModelRenderer(this);
		cube_r38.setPos(0.2246F, -0.5352F, 0.0903F);
		skarf.addChild(cube_r38);
		setRotationAngle(cube_r38, 2.1817F, 0.0F, -3.1416F);
		cube_r38.texOffs(107, 53).addBox(-4.25F, -2.0F, 3.0F, 8.5F, 1.5F, 2.0F, 0.0F, true);

		cube_r39 = new ModelRenderer(this);
		cube_r39.setPos(0.2246F, -1.0352F, -0.1597F);
		skarf.addChild(cube_r39);
		setRotationAngle(cube_r39, -0.1309F, 0.0F, 0.0F);
		cube_r39.texOffs(105, 35).addBox(-4.75F, -1.3F, 3.25F, 9.5F, 2.0F, 2.0F, 0.0F, true);

		cube_r40 = new ModelRenderer(this);
		cube_r40.setPos(0.2246F, -1.0352F, -0.1597F);
		skarf.addChild(cube_r40);
		setRotationAngle(cube_r40, -0.7418F, 0.0F, 0.0F);
		cube_r40.texOffs(106, 62).addBox(-4.75F, -2.0F, 3.35F, 9.5F, 1.5F, 1.5F, 0.0F, true);

		scarfEdge2 = new ModelRenderer(this);
		scarfEdge2.setPos(0.2421F, 24.2327F, 0.5742F);
		skarf.addChild(scarfEdge2);
		

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.8062F, -22.2937F, -2.9448F);
		scarfEdge2.addChild(bone3);
		

		cube_r41 = new ModelRenderer(this);
		cube_r41.setPos(0.1797F, 1.4146F, -0.2322F);
		bone3.addChild(cube_r41);
		setRotationAngle(cube_r41, -0.0393F, -0.0522F, -0.0357F);
		cube_r41.texOffs(110, 6).addBox(-1.6168F, -2.9284F, -0.3173F, 3.0F, 3.0F, 1.0F, 0.0F, true);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-0.2982F, 2.878F, -0.0816F);
		bone3.addChild(bone4);
		

		cube_r42 = new ModelRenderer(this);
		cube_r42.setPos(-0.2721F, -1.4634F, -0.1506F);
		bone4.addChild(cube_r42);
		setRotationAngle(cube_r42, 0.0133F, -0.0905F, 0.1006F);
		cube_r42.texOffs(110, 12).addBox(-1.0704F, -0.07F, -0.4068F, 3.0F, 3.0F, 1.0F, 0.0F, true);

		scarfEdge1 = new ModelRenderer(this);
		scarfEdge1.setPos(1.3726F, 3.5453F, -4.6597F);
		skarf.addChild(scarfEdge1);
		

		bone = new ModelRenderer(this);
		bone.setPos(-0.8658F, -2.4516F, -0.6376F);
		scarfEdge1.addChild(bone);
		

		cube_r43 = new ModelRenderer(this);
		cube_r43.setPos(0.0024F, 1.4283F, -0.0624F);
		bone.addChild(cube_r43);
		setRotationAngle(cube_r43, -0.0436F, -0.0019F, -0.0436F);
		cube_r43.texOffs(120, 6).addBox(-1.44F, -2.9284F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, true);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-0.2822F, -0.8788F, 6.1376F);
		bone.addChild(bone2);
		

		cube_r44 = new ModelRenderer(this);
		cube_r44.setPos(0.2846F, 2.3071F, -6.2F);
		bone2.addChild(cube_r44);
		setRotationAngle(cube_r44, -0.0436F, 0.0019F, 0.0436F);
		cube_r44.texOffs(120, 12).addBox(-1.4353F, -0.0718F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, true);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}