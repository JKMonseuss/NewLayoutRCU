package com.example.udprcu;

public enum KeyEnum {
        REM_KEY_0(0),
        REM_KEY_1(1),
        REM_KEY_2(2),
        REM_KEY_3(3),
        REM_KEY_4(4),
        REM_KEY_5(5),
        REM_KEY_6(6),
        REM_KEY_7(7),
        REM_KEY_8(8),
        REM_KEY_9(9),
        REM_KEY_POWR(10),
        REM_KEY_MUTE(11),
        REM_KEY_LEFT(12),
        REM_KEY_RIGHT(13),
        REM_KEY_DOWN(14),
        REM_KEY_UP(15),
        REM_KEY_PGUP(16),
        REM_KEY_PGDN(17),
        REM_KEY_PLAY(18),
        REM_KEY_STOP(19),
        REM_KEY_FF(20),
        REM_KEY_REW(21),
        REM_KEY_OK(22),
        REM_KEY_MENU(23),
        REM_KEY_EPG(24),
        REM_KEY_TVRAD(25),
        REM_KEY_FAV(26),
        REM_KEY_LAST(27),
        REM_KEY_TEXT(28),
        REM_KEY_BACK(29),
        REM_KEY_RED(30),
        REM_KEY_GREEN(31),
        REM_KEY_YELLOW(32),
        REM_KEY_HELP(33),
        REM_KEY_BLUE(34),
        REM_KEY_REC(35),
        REM_KEY_OPTION(36),
        REM_KEY_MEDIA(37),
        REM_KEY_CHUP(38),
        REM_KEY_CHDN(39),
        REM_KEY_VOUP(40),
        REM_KEY_VODN(41),
        REM_KEY_PORTAL(42),
        REM_KEY_INFO(43),
        REM_KEY_AUDIO(44),
        REM_KEY_HDMI(45),
        REM_KEY_FUNC(46),
        REM_KEY_FACTORY(47),
        REM_KEY_VFORMAT(48),
        REM_KEY_PIP(49),
        REM_KEY_TIMER(50),
        REM_KEY_END(51),
        REM_KEY_EXIT(52),
        REM_KEY_ZOOM(53),
        REM_KEY_DIRECT(54),
        REM_KEY_SETTING(55);

        private final int value;

        private KeyEnum(int value) {
                this.value = value;
        }

        public int getValue() {
                return value;
        }

}
