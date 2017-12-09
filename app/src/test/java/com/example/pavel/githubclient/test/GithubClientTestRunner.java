package com.example.pavel.githubclient.test;

import android.support.annotation.NonNull;

import com.example.pavel.githubclient.BuildConfig;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

public class GithubClientTestRunner extends RobolectricTestRunner {

	private static final int SDK_EMULATE_LEVEL = 23;

	/**
	 * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
	 * and res directory by default. Use the {@link Config} annotation to configure.
	 *
	 * @param testClass the test class to be run
	 * @throws InitializationError if junit says so
	 */
	public GithubClientTestRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}

	@Override
	public Config getConfig(@NonNull Method method) {
		final Config defaultConfig = super.getConfig(method);
		return new Config.Implementation(
				new int[]{SDK_EMULATE_LEVEL},
				defaultConfig.manifest(),
				defaultConfig.qualifiers(),
				defaultConfig.packageName(),
				defaultConfig.resourceDir(),
				defaultConfig.assetDir(),
				defaultConfig.shadows(),
				defaultConfig.instrumentedPackages(),
				defaultConfig.application(),
				defaultConfig.libraries(),
				defaultConfig.constants() == Void.class ? BuildConfig.class : defaultConfig.constants()
		);
	}
}
