package com.khk.backjoonrecommender.service.impl;

import com.khk.backjoonrecommender.common.ResponseCodeMessage;
import com.khk.backjoonrecommender.controller.dto.request.SettingRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.SignUpRequestDTO;
import com.khk.backjoonrecommender.controller.dto.request.UserRequestDTO;
import com.khk.backjoonrecommender.controller.dto.response.*;
import com.khk.backjoonrecommender.entity.Rival;
import com.khk.backjoonrecommender.entity.Setting;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.exception.BaekJoonIdNotFoundException;
import com.khk.backjoonrecommender.exception.handler.AlreadyRegisteredException;
import com.khk.backjoonrecommender.repository.RivalRepository;
import com.khk.backjoonrecommender.repository.SettingRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.UserService;
import com.khk.backjoonrecommender.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.khk.backjoonrecommender.common.ResponseCodeMessage.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BasicUserService implements UserService {
    private final ValidationService validationService;
    private final UserRepository userRepository;
    private final RivalRepository rivalRepository;
    private final SettingRepository settingRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public BasicResponseDto<MyPageResponseDto> findUser(Authentication authentication) {
        String username = authentication.getName();
        User loginUser = userRepository.findByUsername(username);
        Setting userSetting = loginUser.getSetting();

        MyPageResponseDto myPageResponseDto = new MyPageResponseDto(loginUser, userSetting);
        return new BasicResponseDto<>(SUCCESS, SUCCESS_USER_DETAIL, myPageResponseDto);
    }

    @Transactional
    public BasicResponseDto<?> registerUser(SignUpRequestDTO signUpRequestDTO) throws IOException {
        UserRequestDTO userRequestDTO = signUpRequestDTO.getUserRequestDTO();
        SettingRequestDTO settingRequestDTO = signUpRequestDTO.getSettingRequestDTO();

        String baekJoonId = userRequestDTO.getBaekJoonId();
        if (!validationService.validateBaekJoonId(baekJoonId)) {
            throw new BaekJoonIdNotFoundException();
        } else if (!validationService.validateUserRequestInfo(userRequestDTO)) {
            /**
             * ***검증 필요. → validationService.validateUserRequestInfo(userRequestDTO)
             * 1. 아이디가 적절한지(길이 등등)
             * 2. 비밀번호가 적절한지(길이 등등)
             */
            return new BasicResponseDto<>();
        }

        Setting setting = settingRequestDTO.toEntity();
        settingRepository.save(setting);

        encodingUserPassword(userRequestDTO);
        String username = userRequestDTO.getUsername();
        User findUser = userRepository.findByUsername(username);
        if (findUser != null) {
            throw new AlreadyRegisteredException();
        }
        User user = userRequestDTO.toEntity();
        user.setProblemFilterSetting(setting);
        user.resetReloadCount();
        userRepository.save(user);

        BasicResponseDto<User> responseDto = new BasicResponseDto<>();
        responseDto.setCode(SUCCESS);
        responseDto.setMessage(REGISTER_SUCCESS);
        responseDto.setData(null);
        log.info("user {} is created", user.getUsername());

        return responseDto;
    }

    @Override
    public BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<Rival> rivals = rivalRepository.findAllBySelectingUser(user);
        List<RivalListResponseDto> results = rivals.stream()
                .map(rival -> new RivalListResponseDto(rival.getId(), rival.getSelectedUser().getUsername()))
                .collect(Collectors.toList());
        return new BasicResponseDto<>(200, "RIVAL", results);
    }

    @Transactional
    @Override
    public BasicResponseDto<RivalResponseDto> addRival(Long rivalId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Optional<User> optional = userRepository.findById(rivalId);
        Rival savedRival;
        if (optional.isPresent()) {
            Rival rival = new Rival(user, optional.get());
            rival.setUser(user);
            savedRival = rivalRepository.save(rival);
            return new BasicResponseDto<>(200, "RIVAL", new RivalResponseDto(savedRival.getId(), savedRival.getSelectedUser().getUsername()));
        }
        return new BasicResponseDto<>(400, "RIVAL", null);
    }

    @Override
    @Transactional
    public BasicResponseDto<?> deleteRival(Long rivalId) {
        rivalRepository.deleteById(rivalId);
        return new BasicResponseDto<>(200, "RIVAL DELETE", null);
    }

    private void encodingUserPassword(UserRequestDTO userRequestDTO) {
        String password = userRequestDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        userRequestDTO.setPassword(encodedPassword);
    }

    @Transactional
    public BasicResponseDto<?> modifyUser() {
        return null;
    }
}
